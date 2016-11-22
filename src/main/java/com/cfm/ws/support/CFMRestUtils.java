package com.cfm.ws.support;

import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;

import com.cfm.ws.model.response.ResponseEntity;

/**
 * REST API utility class.
 * 
 * 
 */
public final class CFMRestUtils {

	private CFMRestUtils() {
		throw new AssertionError();
	}

	public static Response buildBadRequestResponse(final String errMsg) {
		return Response.status(Response.Status.BAD_REQUEST).entity(buildErrorResponse(errMsg)).build();
	}

	public static Response buildBadRequestResponse(final ResponseEntity response) {
		return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
	}

	public static Response buildTooManyRequestsResponse(final ResponseEntity response) {
		return Response.status(HttpStatus.TOO_MANY_REQUESTS.value()).entity(response).build();
	}

	public static Response buildInternalServerResponse(final String errMsg) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(buildErrorResponse(errMsg)).build();
	}

	public static Response buildNotFoundResponse(final String errMsg) {
		return Response.status(Response.Status.NOT_FOUND).entity(buildErrorResponse(errMsg)).build();
	}

	public static Response buildNotAcceptableResponse(final String errMsg) {
		return Response.status(Response.Status.NOT_ACCEPTABLE).entity(buildErrorResponse(errMsg)).build();
	}

	public static Response buildConflictResponse(final String errMsg) {
		return Response.status(Response.Status.CONFLICT).entity(buildErrorResponse(errMsg)).build();
	}

	public static Response buildNotFoundResponse(final ResponseEntity response) {
		return Response.status(HttpStatus.NOT_FOUND.value()).entity(response).build();
	}

	public static Response buildUnauthorizedResponse(final String errMsg) {
		return Response.status(Response.Status.UNAUTHORIZED).entity(buildErrorResponse(errMsg)).build();
	}

	public static Response buildCreatedResponse(final ResponseEntity response) {
		return Response.status(Response.Status.CREATED).entity(response).build();
	}

	public static Response buildOKResponse(final ResponseEntity response) {
		return Response.status(Response.Status.OK).entity(response).build();
	}

	public static ResponseEntity buildErrorResponse(final String errMsg) {
		final ResponseEntity response = new ResponseEntity();
		response.setSuccess(false);
		response.setErrorMessage(errMsg);
		return response;
	}

}
