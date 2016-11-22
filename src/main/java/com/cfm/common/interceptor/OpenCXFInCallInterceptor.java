package com.cfm.common.interceptor;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cfm.ws.support.CFMConstants;

@Component("openCXFInCallInterceptor")
public class OpenCXFInCallInterceptor extends AbstractPhaseInterceptor<Message> {

	private static final String DEFAULT_HOST = "UnknownHost";

	@Autowired(required = false)
	private String hostname;

	public OpenCXFInCallInterceptor() {
		super(Phase.PRE_INVOKE);
	}

	@Override
	public void handleMessage(final Message message) {
		if (null != message) {
			MDC.clear();
			MDC.put(CFMConstants.HOSTNAME_LOG_KEY_NAME, null != hostname ? hostname : DEFAULT_HOST);
		}
	}
}
