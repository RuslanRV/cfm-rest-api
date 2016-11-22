package com.cfm.ws.service.impl;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfm.common.metric.MetricService;
import com.cfm.ws.service.IMetricRestService;
import com.cfm.ws.support.CFMRestUtils;

@Service("metricRestService")
public class MetricRestService implements IMetricRestService {

	private static final Logger LOGGER = Logger.getLogger(MetricRestService.class);

	@Autowired
	private MetricService metricService;

	@Override
	public Response fetchMetricRegistry() {
		try {
			return CFMRestUtils.buildOKResponse(metricService.fetchServicesCallTimers());
		} catch (final Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			return CFMRestUtils.buildInternalServerResponse(e.getMessage());
		}
	}

	@Override
	public Response healthCheck() {
		try {
			return CFMRestUtils.buildOKResponse(metricService.fetchHealthCheck());
		} catch (final Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			return CFMRestUtils.buildInternalServerResponse(e.getMessage());
		}
	}
}