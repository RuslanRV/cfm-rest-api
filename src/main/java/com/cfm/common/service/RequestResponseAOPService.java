package com.cfm.common.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfm.common.metric.MetricService;
import com.codahale.metrics.Timer.Context;

@Service("requestResponseAOPService")
public class RequestResponseAOPService {

	private static final String FORMAT_OPERATION_NAME = "%s#%s";

	@Autowired
	private MetricService metricService;

	public final Object requestResponseTimeElapsed(final ProceedingJoinPoint pjp) throws Throwable {
		final String operationName = buildTimerOperationName(pjp);
		Object returnObject = null;
		if (null != operationName) {
			Context context = null;
			try {
				context = metricService.startServiceCallTime(operationName);
				returnObject = pjp.proceed();
			} finally {
				metricService.stopServiceCallTime(context);
			}
		}
		return returnObject;
	}

	private String buildTimerOperationName(final ProceedingJoinPoint pjp) {
		try {
			final String methodName = pjp.getSignature().getName();
			final String className = pjp.getSourceLocation().getWithinType().toString();
			return String.format(FORMAT_OPERATION_NAME, className.substring(className.lastIndexOf(".") + 1, className.length()), methodName);
		} catch (final Exception e) {
			// Ignore
			return null;
		}
	}
}
