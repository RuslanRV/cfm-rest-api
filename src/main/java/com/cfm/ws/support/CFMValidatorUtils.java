package com.cfm.ws.support;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 
 * The validator utility class.
 */
public final class CFMValidatorUtils {

	private CFMValidatorUtils() {
		throw new AssertionError();
	}

	/**
	 * Checks if the date is valid.
	 *
	 * @param date the date string
	 * @return @{code true}, if the date is valid otherwise false
	 */
	public static boolean isDateValid(final String date) {
		if (null == date) { return false; }
		final SimpleDateFormat sdf = new SimpleDateFormat(CFMDateUtils.DEFAULT_PATTERN);
		sdf.setLenient(false);
		try { return null != sdf.parse(date); }catch (final ParseException e) { return false;	}
	}
}
