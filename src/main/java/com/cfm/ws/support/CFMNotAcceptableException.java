package com.cfm.ws.support;


@SuppressWarnings("serial")
public class CFMNotAcceptableException extends RuntimeException {

	public CFMNotAcceptableException() {
		super();
	}

	public CFMNotAcceptableException(String message, Throwable cause) {
		super(message, cause);
	}

	public CFMNotAcceptableException(Throwable cause) {
		super(cause);
	}

	public CFMNotAcceptableException(String message) {
		super(message);
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
