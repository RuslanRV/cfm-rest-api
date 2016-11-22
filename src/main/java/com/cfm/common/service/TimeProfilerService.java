package com.cfm.common.service;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

/**
 * Time profiler logger
 * */
@Service("timeProfilerService")
public class TimeProfilerService {

	private static final Logger LOGGER = Logger.getLogger(TimeProfilerService.class.getName());

	private static final String MESSAGE_FORMAT = "| [EXECUTION TIME] for method | %s | %d ms";

	public final Object logTimeElapsed(final ProceedingJoinPoint pjp) throws Throwable {
		final long startTime = System.currentTimeMillis();
		final Object returnObject = pjp.proceed();
		LOGGER.info(createLogMessage(pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), startTime));
		return returnObject;
	}

	private final String createLogMessage(final String className, final String methodName, final long startTime) {
		final String logMessage = String.format(MESSAGE_FORMAT, className.concat(".".concat(methodName)),
				(System.currentTimeMillis() - startTime));
		return logMessage;
	}
}
