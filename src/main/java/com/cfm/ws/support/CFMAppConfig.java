package com.cfm.ws.support;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This class is used for retrieving data from application.properties file.
 * 
 * */
public final class CFMAppConfig {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("application");

	private CFMAppConfig() {
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

	public static boolean get(final String key, final boolean defaultValue) {
		try {
			return Boolean.valueOf(BUNDLE.getString(key));
		} catch (final Exception e) {
			return defaultValue;
		}
	}
}
