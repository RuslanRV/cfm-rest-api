package com.cfm.common.provider;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Component;

import com.cfm.ws.model.response.ResponseEntity;

/**
 * The JSON mapping error exception provider class to catch incorrect structure
 * of JSON request.
 *
 */
@Component("jsonMappingErrorExceptionProvider")
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JsonMappingErrorExceptionProvider implements ExceptionMapper<JsonMappingException> {

	private static final Logger LOGGER = Logger.getLogger(JsonMappingErrorExceptionProvider.class);

	@Override
	public Response toResponse(final JsonMappingException ex) {
		final ResponseEntity response = new ResponseEntity();
		response.setSuccess(false);
		response.setErrorMessage(ex.getMessage());
		LOGGER.error(ExceptionUtils.getStackTrace(ex));
		return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(response).build();
	}
}
