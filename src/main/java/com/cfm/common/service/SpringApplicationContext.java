package com.cfm.common.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * The application context instance.
 * */
@Component("springApplicationContext")
public class SpringApplicationContext implements ApplicationContextAware {

	private static ApplicationContext context;

	public ApplicationContext getApplicationContext() {
		return context;
	}

	@Override
	public void setApplicationContext(final ApplicationContext ctx) {
		context = ctx;
	}

}
