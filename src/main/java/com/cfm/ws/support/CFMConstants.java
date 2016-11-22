package com.cfm.ws.support;

public final class CFMConstants {

	public static final String Y = "Y";
	public static final String N = "N";
	/** No error, operation successful. */
	public static final int HTTP_STATUS_200 = 200;
	/** No error, operation successful. */
	public static final int HTTP_STATUS_201 = 201;
	/** Malformed syntax or a bad query. */
	public static final int HTTP_STATUS_400 = 400;
	/** Resource not found. */
	public static final int HTTP_STATUS_404 = 404;
	/** Internal server error. */
	public static final int HTTP_STATUS_500 = 500;
	
	public static final String HOSTNAME_LOG_KEY_NAME = "HOSTNAME";

	private CFMConstants() {
		throw new AssertionError();
	}
}
