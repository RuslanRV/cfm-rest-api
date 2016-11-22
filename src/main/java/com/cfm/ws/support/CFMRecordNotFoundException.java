package com.cfm.ws.support;

import com.cfm.ws.model.response.ResponseEntity;

/**
 * The Record not found exception class. This exception class should be used if
 * a DB query returns zero record.
 * 
 * */
@SuppressWarnings("serial")
public class CFMRecordNotFoundException extends RuntimeException {

	private ResponseEntity response;

	public ResponseEntity getResponse() {
		return response;
	}

	public CFMRecordNotFoundException() {
		super();
	}

	public CFMRecordNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CFMRecordNotFoundException(Throwable cause) {
		super(cause);
	}

	public CFMRecordNotFoundException(String message) {
		super(message);
	}

	public CFMRecordNotFoundException(ResponseEntity inputResponse, String message) {
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
