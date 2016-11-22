package com.cfm.ws.support;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

/**
 * String & Collection utility class.
 * 
 * */
// @Immutable
public final class CFMStringUtils extends StringUtils {

	private CFMStringUtils() {
		throw new AssertionError();
	}

	/**
	 * <p>
	 * Checks if any String(s) is empty ("") or null.
	 * </p>
	 * 
	 * @param cs the String(s) to check
	 * @return {@code true} if any String(s) is empty or null
	 */
	public static boolean isEmpty(final String... cs) {

		if (cs != null && cs.length > 0) {
			for (String str : cs) {
				if (isEmpty(str)) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if a String(s) is not empty ("") and not null.
	 * </p>
	 * 
	 * @param cs the String(s) to check
	 * @return {@code true} if the String(s) is not empty and not null
	 */
	public static boolean isNotEmpty(final String... cs) {
		return !isEmpty(cs);
	}

	/**
	 * <p>
	 * Checks if a Object is null.
	 * </p>
	 * 
	 * @param obj the Object to check
	 * @return {@code String} if the Object is null
	 */
	public static String makeEmptyIfNull(final Object obj) {
		return (obj == null) ? EMPTY : obj.toString();
	}

	/**
	 * <p>
	 * Checks if a Collection is empty ("") or null.
	 * </p>
	 * 
	 * @param list the Collection to check
	 * @return {@code true} if the Collection is empty or null
	 */
	public static boolean isEmpty(final Collection<?> list) {
		return list == null || list.isEmpty();
	}

	/**
	 * <p>
	 * Checks if a Collection(s) is empty ("") or null.
	 * </p>
	 * 
	 * @param list the Collection(s) to check
	 * @return {@code true} if the Collection(s) is empty or null
	 */
	public static boolean isEmpty(final Collection<?>... list) {

		if (isNotEmpty(list)) {
			for (Collection<?> str : list) {
				if (isEmpty(str)) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if a Collection(s) is not empty ("") and not null.
	 * </p>
	 * 
	 * @param list the Collection(s) to check
	 * @return {@code true} if the Collection(s) is not empty and not null
	 */
	public static boolean isNotEmpty(final Collection<?>... list) {
		return !isEmpty(list);
	}

	/**
	 * <p>
	 * Checks if a Object(s) is null.
	 * </p>
	 * 
	 * @param obj the Object to check
	 * @return {@code true} if the Object(s) is null
	 */
	public static boolean isNull(final Object... obj) {
		if (obj != null) {
			for (Object ob : obj) {
				if (ob == null) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * <p>
	 * Checks if a Object(s) is not null.
	 * </p>
	 * 
	 * @param obj the Object to check
	 * @return {@code true} if the Object(s) is not null
	 */
	public static boolean isNotNull(final Object... obj) {
		return !isNull(obj);
	}

	public static boolean isLessThan(final int strLen, final String arg) {
		return arg.length() < strLen;
	}

	public static boolean isNotLessThan(final int strLen, final String arg) {
		return !isLessThan(strLen, arg);
	}

	public static boolean isGreaterThan(final int strLen, final String arg) {
		return arg.length() > strLen;
	}

	public static boolean isNotGreaterThan(final int strLen, final String arg) {
		return !isGreaterThan(strLen, arg);
	}

	/**
	 * <p>
	 * Checks if a Collection is not empty ("") and not null.
	 * </p>
	 * 
	 * @param list the Collection to check
	 * @return {@code true} if the Collection is not empty and not null
	 */
	public static boolean isNotEmpty(final Collection<?> list) {
		return !isEmpty(list);
	}

	/**
	 * Make string (Y or N) as {@code true} or {@code false}
	 * 
	 * @param arg string Y or N
	 * @return {@code true} if arg equeals Y otherwise {@code false}
	 */
	public static boolean makeTrueOrFalseYN(final String arg) {
		return CFMConstants.Y.equals(arg);
	}

	/**
	 * Make null if arg is empty.
	 * 
	 * @param arg string
	 * @return null if empty otherwise itself
	 */
	public static String makeNullIfEmpty(final String arg) {
		return isEmpty(arg) ? null : arg;
	}
}
