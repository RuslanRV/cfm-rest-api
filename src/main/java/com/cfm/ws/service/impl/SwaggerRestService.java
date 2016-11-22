package com.cfm.ws.service.impl;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfm.common.swagger.SwaggerMetricService;
import com.cfm.ws.service.ISwaggerRestService;
import com.cfm.ws.support.CFMRestUtils;

@Service("swaggerRestService")
public class SwaggerRestService implements ISwaggerRestService {

	private static final Logger LOGGER = Logger.getLogger(SwaggerRestService.class);

	@Autowired
	private SwaggerMetricService swaggerMetricService;

	@Override
	public Response fatchDevHealthCheck() {

		try {
			return Response.status(Response.Status.OK).entity(swaggerMetricService.callDevHealthCheck()).build();
		} catch (final Exception e) {
			LOGGER.warn(ExceptionUtils.getStackTrace(e));
			return CFMRestUtils.buildBadRequestResponse(e.getMessage());
		}
	}

	@Override
	public Response fatchDevMetricRegistry() {

		try {
			return Response.status(Response.Status.OK).entity(swaggerMetricService.callDevMetricRegistry()).build();
		} catch (final Exception e) {
			LOGGER.warn(ExceptionUtils.getStackTrace(e));
			return CFMRestUtils.buildBadRequestResponse(e.getMessage());
		}
	}


	@Override
	public Response fatchProdHealthCheck() {

		try {
			return Response.status(Response.Status.OK).entity(swaggerMetricService.callProdHealthCheck()).build();
		} catch (final Exception e) {
			LOGGER.warn(ExceptionUtils.getStackTrace(e));
			return CFMRestUtils.buildBadRequestResponse(e.getMessage());
		}
	}

	@Override
	public Response fatchProdMetricRegistry() {

		try {
			return Response.status(Response.Status.OK).entity(swaggerMetricService.callProdMetricRegistry()).build();
		} catch (final Exception e) {
			LOGGER.warn(ExceptionUtils.getStackTrace(e));
			return CFMRestUtils.buildBadRequestResponse(e.getMessage());
		}
	}

}
