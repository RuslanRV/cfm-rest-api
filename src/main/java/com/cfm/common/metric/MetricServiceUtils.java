package com.cfm.common.metric;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cfm.ws.model.AppProperties;
import com.cfm.ws.model.CurrentLogger;
import com.cfm.ws.model.DBConnection;
import com.cfm.ws.support.CFMAppConfig;
import com.cfm.ws.support.DBConfig;
import com.codahale.metrics.Timer;

/**
 * The utility class for the {@link MetricService} bean class.
 * */
public final class MetricServiceUtils {

	private static final String NOT_SPECIFIED = "NOT SPECIFIED";

	private MetricServiceUtils() {

	}

	public static AppProperties getAppProperties() {
		final AppProperties ap = new AppProperties();
		ap.setCxfJaxrsBeanAddress(CFMAppConfig.get("cxf.jaxrs.bean.address", NOT_SPECIFIED));
		ap.setJaxrsRestServerAddress(CFMAppConfig.get("jaxrs.rest.server.address", NOT_SPECIFIED));
		ap.setSwaggerBasePath(CFMAppConfig.get("swagger.base.path", NOT_SPECIFIED));
		ap.setSwaggerContact(CFMAppConfig.get("swagger.contact", "helpdesk"));
		ap.setSwaggerDescription(CFMAppConfig.get("swagger.description", NOT_SPECIFIED));
		ap.setSwaggerLicense(CFMAppConfig.get("swagger.license", NOT_SPECIFIED));
		ap.setSwaggerLicenseUrl(CFMAppConfig.get("swagger.license.url", NOT_SPECIFIED));
		ap.setSwaggerScan(CFMAppConfig.get("swagger.scan", false));
		ap.setSwaggerTitle(CFMAppConfig.get("swagger.title", NOT_SPECIFIED));
		ap.setSwaggerVersion(CFMAppConfig.get("swagger.version", NOT_SPECIFIED));
		ap.setWadlGeneratorAppTitle(CFMAppConfig.get("wadl.generator.application.title", NOT_SPECIFIED));
		ap.setWadlGeneratorLinkJsonToXmlSchema(CFMAppConfig.get("wadl.generator.link.json.to.xml.schema", false));
		return ap;
	}

	/**
	 * Fetch DB connection info.
	 *
	 * @return the {@link DBConnection}
	 */
	public static DBConnection getDBConnInfo() {
		final DBConnection sc = new DBConnection();
		sc.setDriver(DBConfig.get("db.driver", StringUtils.EMPTY));
		sc.setJdbcurl(DBConfig.get("db.jdbcurl", StringUtils.EMPTY));
		sc.setUsername(DBConfig.get("db.username", StringUtils.EMPTY));
		return sc;
	}
	
	public static String getTimerRateInMsWith2Decimal(final Timer timer) {
		final Double responseTime = timer.getSnapshot().getMedian() / 1000000;
		return String.format("%sms", new DecimalFormat("#.00").format(responseTime));
	}

	public static List<CurrentLogger> getLoggers() {

		@SuppressWarnings("unchecked")
		final Enumeration<Logger> e = (Enumeration<Logger>) LogManager.getCurrentLoggers();
		final List<CurrentLogger> loggers = new ArrayList<CurrentLogger>();

		while (e.hasMoreElements()) {
			final Logger log = e.nextElement();
			final CurrentLogger logger = new CurrentLogger();
			logger.setLevel(getLevel(log));
			logger.setLogger(log.getName());
			loggers.add(logger);
		}
		return loggers;
	}

	private static String getLevel(final Logger logger) {
		Level level = logger.getLevel();
		if (level == null && logger.getParent() != null) {
			level = logger.getParent().getLevel();
		}
		return null != level ? level.toString() : StringUtils.EMPTY;
	}
}
