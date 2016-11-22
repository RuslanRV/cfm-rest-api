package com.cfm.ws.support;

@SuppressWarnings("serial")
public class CFMConflictException extends RuntimeException {

	public CFMConflictException() {
		super();
	}

	public CFMConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public CFMConflictException(Throwable cause) {
		super(cause);
	}

	public CFMConflictException(String message) {
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
