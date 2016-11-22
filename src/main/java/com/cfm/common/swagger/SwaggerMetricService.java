package com.cfm.common.swagger;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cfm.ws.support.CFMAppConfig;

/**
 * This class used for REST API calls.
 */
@Service("swaggerMetricService")
public class SwaggerMetricService {

	private SimpleClientHttpRequestFactory requestFactory;

	@SuppressWarnings("unused")
	private final void init() {
		requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(CFMAppConfig.get("swagger.request.timeout", 1));
	}

	/** Dev: Environment, Health Check */
	public String callDevHealthCheck() throws Exception {
		return callRestService(CFMAppConfig.get("swagger.dev.healthcheck.url", StringUtils.EMPTY));
	}

	/** Dev: Environment, Metric Registry */
	public String callDevMetricRegistry() throws Exception {
		return callRestService(CFMAppConfig.get("swagger.dev.metricregistry.url", StringUtils.EMPTY));
	}

	/** Test: Environment, Health Check */
	public String callTestHealthCheck() throws Exception {
		return callRestService(CFMAppConfig.get("swagger.test.healthcheck.url", StringUtils.EMPTY));
	}

	/** Test: Environment, Metric Registry */
	public String callTestMetricRegistry() throws Exception {
		return callRestService(CFMAppConfig.get("swagger.test.metricregistry.url", StringUtils.EMPTY));
	}

	/** QA: Environment, Health Check */
	public String callQAHealthCheck() throws Exception {
		return callRestService(CFMAppConfig.get("swagger.qa.healthcheck.url", StringUtils.EMPTY));
	}

	/** QA: Environment, Metric Registry */
	public String callQAMetricRegistry() throws Exception {
		return callRestService(CFMAppConfig.get("swagger.qa.metricregistry.url", StringUtils.EMPTY));
	}

	/** Production: Environment, Health Check */
	public String callProdHealthCheck() throws Exception {
		return callRestService(CFMAppConfig.get("swagger.prod.healthcheck.url", StringUtils.EMPTY));
	}

	/** Production: Environment, Metric Registry */
	public String callProdMetricRegistry() throws Exception {
		return callRestService(CFMAppConfig.get("swagger.prod.metricregistry.url", StringUtils.EMPTY));
	}

	private final String callRestService(final String url) throws Exception {
		final HttpHeaders headers = new HttpHeaders();

		final List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		headers.setAccept(acceptableMediaTypes);
		final HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

		final RestTemplate restTemplate = new RestTemplate(requestFactory);

		try {
			return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class).getBody();
		} catch (final Exception ex) {
			throw ex;
		}
	}

}
