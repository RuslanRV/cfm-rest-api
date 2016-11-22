package com.cfm.common.provider;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cfm.ws.model.response.ResponseEntity;

/**
 * The web application error exception provider class to catch web application
 * errors.
 *
 */
@Component("webAppErrorExceptionProvider")
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class WebAppErrorExceptionProvider implements ExceptionMapper<WebApplicationException> {

	private static final Logger LOGGER = Logger.getLogger(WebAppErrorExceptionProvider.class);

	@Override
	public Response toResponse(final WebApplicationException ex) {
		ResponseEntity response = new ResponseEntity();
		response.setSuccess(false);
		if (ex.getResponse().getEntity() instanceof ResponseEntity) {
			response = (ResponseEntity) ex.getResponse().getEntity();
		} else {
			response.setErrorMessage(ex.getMessage());
		}
		LOGGER.error(ExceptionUtils.getStackTrace(ex));
		return Response.status(ex.getResponse().getStatus()).type(MediaType.APPLICATION_JSON).entity(response).build();
	}
}
