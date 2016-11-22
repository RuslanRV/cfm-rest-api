package com.cfm.ws.support;

import com.cfm.ws.model.response.ResponseEntity;

/**
 * The Validator exception class.
 * 
 * */
@SuppressWarnings("serial")
public class CFMValidatorException extends Exception {

	private ResponseEntity response;

	public ResponseEntity getResponse() {
		return response;
	}

	public CFMValidatorException() {
		super();
	}

	public CFMValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public CFMValidatorException(Throwable cause) {
		super(cause);
	}

	public CFMValidatorException(String message) {
		super(message);
	}

	public CFMValidatorException(ResponseEntity inputResponse, String message) {
		super(message);
		response = inputResponse;
	}

	@Override
	public String getMessage() {
		return this.toString();
	}

	@Override
	public String toString() {
		if (super.getCause() != null) {
			return String.format("%s %s", CFMStringUtils.makeEmptyIfNull(super.getMessage()), super.getCause()
					.toString());
		}
		return super.getMessage();
	}

}
