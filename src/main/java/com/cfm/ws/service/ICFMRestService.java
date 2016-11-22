package com.cfm.ws.service;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cfm.ws.model.response.BalanceResponse;
import com.cfm.ws.support.CFMConstants;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Consumes(MediaType.MEDIA_TYPE_WILDCARD)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "cfmRestService", description = "API ontop of CFM")
@WebService(name = "cfmRestService")
@Path("cfmRestService")
public interface ICFMRestService {

	@GET
	@Path("/balanceOfPlayer/{playerId}")
	@ApiOperation(value = "Player Id", notes = "GREEN retrieves a player ID and returns his current balance.", response = BalanceResponse.class)
	@ApiResponses(value = { @ApiResponse(code = CFMConstants.HTTP_STATUS_404, message = "Balance not found"),
			@ApiResponse(code = CFMConstants.HTTP_STATUS_400, message = "Invalid Player ID supplied"),
			@ApiResponse(code = CFMConstants.HTTP_STATUS_500, message = "Server Error") })
	public Response balanceOfPlayer(@ApiParam(required = true, value = "Player unique ID.") @PathParam("playerId") int playerId);
}
