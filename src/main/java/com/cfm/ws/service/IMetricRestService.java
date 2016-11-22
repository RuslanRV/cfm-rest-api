package com.cfm.ws.service;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cfm.ws.model.response.HealthCheckResponse;
import com.cfm.ws.model.response.MetricRegistryResponse;
import com.cfm.ws.support.CFMConstants;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "metricRestService", description = "API ontop of CFM")
@WebService(name = "metricRestService")
@Path("metricRestService")
public interface IMetricRestService {

	@GET
	@Path("/fetchMetricRegistry")
	@ApiOperation(value = "No input parameter required", notes = "YELLOW Fetch the API Services Call Counter", response = MetricRegistryResponse.class)
	@ApiResponse(code = CFMConstants.HTTP_STATUS_500, message = "Server Error")
	public Response fetchMetricRegistry();

	@GET
	@Path("/healthCheck")
	@ApiOperation(value = "No input parameter required", notes = "YELLOW Fetch the API DB Health Check", response = HealthCheckResponse.class)
	@ApiResponse(code = CFMConstants.HTTP_STATUS_500, message = "Server Error")
	public Response healthCheck();
}