package com.cfm.ws.model.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.cfm.ws.model.ServicesRegistry;

@XmlRootElement
public class MetricRegistryResponse extends ResponseEntity {

	private List<ServicesRegistry> registries;

	public List<ServicesRegistry> getRegistries() {
		return registries;
	}

	public void setRegistries(List<ServicesRegistry> registries) {
		this.registries = registries;
	}

	public void addRegistry(final ServicesRegistry registry) {
		if (null == registries) {
			registries = new ArrayList<ServicesRegistry>();
		}
		registries.add(registry);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}