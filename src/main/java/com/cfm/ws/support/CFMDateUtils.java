package com.cfm.ws.support;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * Date utility class.
 * */
// @Immutable
public final class CFMDateUtils extends DateUtils {

	private static final Date EMPTY_DATE = null;
	public static final String DEFAULT_PATTERN = "MM/dd/yyyy";
	public static final String API_PATTERN = "MM/DD/YYYY";
	public static final int SECOND = 1000;
	public static final int MINUTE = 60 * SECOND;
	public static final int HOUR = 60 * MINUTE;
	public static final int DAY = 24 * HOUR;

	private CFMDateUtils() {
		throw new AssertionError();
	}

	/**
	 * Format date from {@code String} to {@code Date}.
	 *
	 * @param input the input date string
	 * @return the formated @{code Date} if no parse exception and the input is
	 *         not empty otherwise {@code null}
	 */
	public static Date formatDate(final String input) {

		if (CFMStringUtils.isEmpty(input)) {
			return EMPTY_DATE;
		}
		final DateFormat inputFormatter = new SimpleDateFormat(DEFAULT_PATTERN);
		try {
			return inputFormatter.parse(input);
		} catch (final ParseException e) {
			return EMPTY_DATE;
		}
	}

	public static String getCurrentGMTTime() {
		return Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime().toString();
	}

	public static long getDateInMilliseconds(final String date) {
		return formatDate(date).getTime();
	}

	public static String convertMsToHumanReadable(final long ms) {

		long localMS = ms;
		final StringBuffer sb = new StringBuffer(StringUtils.EMPTY);

		if (localMS > DAY) {
			sb.append(localMS / DAY).append(" days ");
			localMS %= DAY;
		}
		if (localMS > HOUR) {
			sb.append(localMS / HOUR).append(" hours ");
			localMS %= HOUR;
		}
		if (localMS > MINUTE) {
			sb.append(localMS / MINUTE).append(" minutes ");
			localMS %= MINUTE;
		}
		if (localMS > SECOND) {
			sb.append(localMS / SECOND).append(" seconds ");
			localMS %= SECOND;
		}
		return sb.append(localMS + " ms").toString();
	}

}
