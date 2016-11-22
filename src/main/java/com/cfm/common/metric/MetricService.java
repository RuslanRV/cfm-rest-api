package com.cfm.common.metric;

import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import com.cfm.common.service.SpringApplicationContext;
import com.cfm.ws.model.DBConnection;
import com.cfm.ws.model.JVMProperties;
import com.cfm.ws.model.ServicesRegistry;
import com.cfm.ws.model.response.HealthCheckResponse;
import com.cfm.ws.model.response.MetricRegistryResponse;
import com.cfm.ws.support.CFMDateUtils;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SlidingTimeWindowReservoir;
import com.codahale.metrics.Timer;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheck.Result;
import com.codahale.metrics.health.HealthCheckRegistry;

@Service("metricService")
public class MetricService {

	private static final Logger LOGGER = Logger.getLogger(MetricService.class.getName());

	private static final String DB_HEALTH_CHECK_NAME = "DB_HEALTH_CHECK_NAME";
	private static final int MB = 1024 * 1024;

	private MetricRegistry registry;
	private HealthCheckRegistry healthChecks;
	private ConcurrentMap<String, SlidingTimeWindowReservoir> stwReservoirs1Hour;
	private ConcurrentMap<String, SlidingTimeWindowReservoir> stwReservoirs24Hours;

	@Autowired
	private SpringApplicationContext springApplicationContext;
	@Autowired
	private DriverManagerDataSource dataSource;

	/**
	 * Runs on server start up and Registers an application registers.
	 */
	@SuppressWarnings("unused")
	private final void init() {
		this.registry = new MetricRegistry();
		this.healthChecks = new HealthCheckRegistry();
		healthChecks.register(DB_HEALTH_CHECK_NAME, new DBHealthCheck());
		stwReservoirs1Hour = new ConcurrentHashMap<String, SlidingTimeWindowReservoir>();
		stwReservoirs24Hours = new ConcurrentHashMap<String, SlidingTimeWindowReservoir>();
	}

	public Timer.Context startServiceCallTime(final String operationName) {
		update1hourAnd24HoursCalls(operationName);
		return registry.timer(MetricRegistry.name(operationName)).time();
	}

	public final void update1hourAnd24HoursCalls(final String operationName) {
		if (null == operationName) {
			return;
		}
		// Update 1 hour
		if (!stwReservoirs1Hour.containsKey(operationName)) {
			stwReservoirs1Hour.put(operationName, new SlidingTimeWindowReservoir(1L, TimeUnit.HOURS));
		}
		stwReservoirs1Hour.get(operationName).update(1L);

		// Update 24 hours
		if (!stwReservoirs24Hours.containsKey(operationName)) {
			stwReservoirs24Hours.put(operationName, new SlidingTimeWindowReservoir(24L, TimeUnit.HOURS));
		}
		stwReservoirs24Hours.get(operationName).update(1L);
	}

	public final long get1hourCallsCount(final String operationName) {
		final SlidingTimeWindowReservoir stwr = stwReservoirs1Hour.get(operationName);
		return null != stwr ? stwr.getSnapshot().getValues().length : 0L;
	}

	public final long get24hoursCallsCount(final String operationName) {
		final SlidingTimeWindowReservoir stwr = stwReservoirs24Hours.get(operationName);
		return null != stwr ? stwr.getSnapshot().getValues().length : 0L;
	}

	/**
	 * Fetch all services call timers.
	 *
	 * @return the {@link MetricRegistryResponse}
	 */
	public MetricRegistryResponse fetchServicesCallTimers() {
		final Iterator<Entry<String, Timer>> timers = registry.getTimers().entrySet().iterator();
		final MetricRegistryResponse response = new MetricRegistryResponse();
		while (timers.hasNext()) {
			final Entry<String, Timer> next = timers.next();
			final Timer timer = next.getValue();
			final String operationName = next.getKey();
			final ServicesRegistry sr = new ServicesRegistry();
			sr.setAverageResponse(MetricServiceUtils.getTimerRateInMsWith2Decimal(timer));
			sr.setOperationName(operationName);
			sr.setCallCounter(timer.getCount());
			sr.setCalledLastHour(get1hourCallsCount(operationName));
			sr.setCalledLast24Hours(get24hoursCallsCount(operationName));
			response.addRegistry(sr);
		}
		return response;
	}

	/**
	 * Fetch service call timer by operationName.
	 * 
	 * @return the {@link MetricRegistryResponse}
	 */
	public MetricRegistryResponse fetchServiceCallTimer(final String operationName) {
		final Timer timer = registry.getTimers().get(operationName);
		final MetricRegistryResponse response = new MetricRegistryResponse();
		final ServicesRegistry sr = new ServicesRegistry();
		if (null == timer) {
			sr.setAverageResponse(StringUtils.EMPTY);
			sr.setCallCounter(0L);
			sr.setCalledLastHour(0L);
			sr.setCalledLast24Hours(0L);
		} else {
			sr.setAverageResponse(MetricServiceUtils.getTimerRateInMsWith2Decimal(timer));
			sr.setCallCounter(timer.getCount());
			sr.setCalledLastHour(get1hourCallsCount(operationName));
			sr.setCalledLast24Hours(get24hoursCallsCount(operationName));
		}
		sr.setOperationName(operationName);
		response.addRegistry(sr);
		return response;
	}

	/**
	 * Stops recording the elapsed time, updates the timer and returns the
	 * elapsed time in nanoseconds.
	 * 
	 * @param context
	 *            {@link Timer.Context }
	 * @return elapsed time in nanoseconds.
	 */
	public long stopServiceCallTime(final Timer.Context context) {
		return null != context ? context.stop() : 0L;
	}

	/**
	 * Runs the health check with the {@link MetricService#DB_HEALTH_CHECK_NAME}
	 * name and fetch the health checks.
	 *
	 * @return the {@link HealthCheckResponse}
	 */
	public HealthCheckResponse fetchHealthCheck() {

		final HealthCheckResponse hcr = new HealthCheckResponse();
		final Result result = healthChecks.runHealthCheck(DB_HEALTH_CHECK_NAME);
		final DBConnection sc = MetricServiceUtils.getDBConnInfo();

		if (result.isHealthy()) {
			hcr.setStatus(HealthCheckResponse.STATUS.GREEN.toString());
		} else {
			hcr.setStatus(HealthCheckResponse.STATUS.RED.toString());
		}

		hcr.setDbConnection(sc);
		hcr.setAppProperties(MetricServiceUtils.getAppProperties());
		hcr.setJvm(getJVMProperties());
		hcr.setCurrentLoggers(MetricServiceUtils.getLoggers());
		return hcr;
	}

	/**
	 * Gets the JVM properties.
	 *
	 * @return the {@link JVMProperties}
	 */
	private JVMProperties getJVMProperties() {

		final JVMProperties jp = new JVMProperties();
		final Runtime runtime = Runtime.getRuntime();
		final long memoryUsage = (runtime.totalMemory() / MB) - (runtime.freeMemory() / MB);

		jp.setApplicationName(springApplicationContext.getApplicationContext().getApplicationName());
		jp.setCurrentMemoryUsage(memoryUsage + "MB");
		jp.setJavaRuntimeName(System.getProperty("java.runtime.name"));
		jp.setJavaRuntimeVersion(System.getProperty("java.runtime.version"));
		jp.setJavaSpecVersion(System.getProperty("java.specification.version"));
		jp.setJavaVersion(System.getProperty("java.version"));
		jp.setJavaVMName(System.getProperty("java.vm.name"));
		jp.setOsName(System.getProperty("os.name"));
		jp.setUpTime(CFMDateUtils.convertMsToHumanReadable(ManagementFactory.getRuntimeMXBean().getUptime()));
		return jp;
	}

	public MetricRegistry getRegistry() {
		return registry;
	}

	private class DBHealthCheck extends HealthCheck {

		@Override
		public HealthCheck.Result check() {

			try {
				final ExecutorService service = Executors.newFixedThreadPool(1);
				final DBHealthCheckCallable callable = new DBHealthCheckCallable();
				final Future<String> futureResult = service.submit(callable);
				try {
					final String fr = futureResult.get(10000L, TimeUnit.MILLISECONDS);
					if (StringUtils.isNotEmpty(fr)) {
						LOGGER.error(fr);
						return HealthCheck.Result.unhealthy("Not able to connect to DB."); 
					}
				} catch (final Exception te) {
					LOGGER.error(ExceptionUtils.getStackTrace(te));
					return HealthCheck.Result.unhealthy("Not able to connect to DB.");
				} finally {
					service.shutdown();
				}
			} catch (final Exception e) {
				LOGGER.error(ExceptionUtils.getStackTrace(e));
				return HealthCheck.Result.unhealthy(e);
			}
			return HealthCheck.Result.unhealthy("Not able to connect to DB.");
		}
	}

	private final class DBHealthCheckCallable implements Callable<String> {

		@Override
		public String call() throws Exception {

			Connection conn = null;

			try {
				conn = dataSource.getConnection();
				final PreparedStatement ps = conn.prepareStatement("/* ping */ SELECT 1");
				final ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					if(rs.getInt("1") == 1){
						return StringUtils.EMPTY;
					}
				}
			} catch (final SQLException ex) {
				// ignore failure closing connection
				try {
					conn.close();
				} catch (final SQLException e) {
				}
				throw ex;
			} finally {
				DataSourceUtils.releaseConnection(conn, dataSource);
			}
			return StringUtils.EMPTY;
		}
	}

}
