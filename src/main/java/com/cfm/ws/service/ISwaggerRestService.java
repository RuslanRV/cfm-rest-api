package com.cfm.ws.service;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.WILDCARD)
@Produces(MediaType.APPLICATION_JSON)
@WebService(name = "swaggerRestService")
@Path("swaggerRestService")
public interface ISwaggerRestService {

	@GET
	@Path("/fatchDevHealthCheck")
	public Response fatchDevHealthCheck();

	@GET
	@Path("/fatchDevMetricRegistry")
	public Response fatchDevMetricRegistry();

	@GET
	@Path("/fatchProdHealthCheck")
	public Response fatchProdHealthCheck();

	@GET
	@Path("/fatchProdMetricRegistry")
	public Response fatchProdMetricRegistry();
}
