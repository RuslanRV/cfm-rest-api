package com.cfm.ws.support;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This class is used for retrieving data from siebelcfg.properties file.
 * 
 * */
public final class DBConfig {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("dbcfg");

	private DBConfig() {
		throw new AssertionError();
	}

	public static String get(final String key, final String defaultValue) {
		try {
			return BUNDLE.getString(key);
		} catch (final MissingResourceException e) {
			return defaultValue;
		}
	}

	public static int get(final String key, final int defaultValue) {
		try {
			return Integer.valueOf(BUNDLE.getString(key));
		} catch (final MissingResourceException e) {
			return defaultValue;
		}
	}

}
