package com.cfm.ws.model;

public class AppProperties {

	private String wadlGeneratorAppTitle;
	private Boolean wadlGeneratorLinkJsonToXmlSchema;
	private String jaxrsRestServerAddress;
	private String cxfJaxrsBeanAddress;
	private String swaggerVersion;
	private String swaggerBasePath;
	private String swaggerTitle;
	private String swaggerDescription;
	private String swaggerContact;
	private String swaggerLicense;
	private String swaggerLicenseUrl;
	private Boolean swaggerScan;

	public String getWadlGeneratorAppTitle() {
		return wadlGeneratorAppTitle;
	}

	public void setWadlGeneratorAppTitle(String wadlGeneratorAppTitle) {
		this.wadlGeneratorAppTitle = wadlGeneratorAppTitle;
	}

	public Boolean getWadlGeneratorLinkJsonToXmlSchema() {
		return wadlGeneratorLinkJsonToXmlSchema;
	}

	public void setWadlGeneratorLinkJsonToXmlSchema(Boolean wadlGeneratorLinkJsonToXmlSchema) {
		this.wadlGeneratorLinkJsonToXmlSchema = wadlGeneratorLinkJsonToXmlSchema;
	}

	public String getJaxrsRestServerAddress() {
		return jaxrsRestServerAddress;
	}

	public void setJaxrsRestServerAddress(String jaxrsRestServerAddress) {
		this.jaxrsRestServerAddress = jaxrsRestServerAddress;
	}

	public String getCxfJaxrsBeanAddress() {
		return cxfJaxrsBeanAddress;
	}

	public void setCxfJaxrsBeanAddress(String cxfJaxrsBeanAddress) {
		this.cxfJaxrsBeanAddress = cxfJaxrsBeanAddress;
	}

	public String getSwaggerVersion() {
		return swaggerVersion;
	}

	public void setSwaggerVersion(String swaggerVersion) {
		this.swaggerVersion = swaggerVersion;
	}

	public String getSwaggerBasePath() {
		return swaggerBasePath;
	}

	public void setSwaggerBasePath(String swaggerBasePath) {
		this.swaggerBasePath = swaggerBasePath;
	}

	public String getSwaggerTitle() {
		return swaggerTitle;
	}

	public void setSwaggerTitle(String swaggerTitle) {
		this.swaggerTitle = swaggerTitle;
	}

	public String getSwaggerDescription() {
		return swaggerDescription;
	}

	public void setSwaggerDescription(String swaggerDescription) {
		this.swaggerDescription = swaggerDescription;
	}

	public String getSwaggerContact() {
		return swaggerContact;
	}

	public void setSwaggerContact(String swaggerContact) {
		this.swaggerContact = swaggerContact;
	}

	public String getSwaggerLicense() {
		return swaggerLicense;
	}

	public void setSwaggerLicense(String swaggerLicense) {
		this.swaggerLicense = swaggerLicense;
	}

	public String getSwaggerLicenseUrl() {
		return swaggerLicenseUrl;
	}

	public void setSwaggerLicenseUrl(String swaggerLicenseUrl) {
		this.swaggerLicenseUrl = swaggerLicenseUrl;
	}

	public Boolean getSwaggerScan() {
		return swaggerScan;
	}

	public void setSwaggerScan(Boolean swaggerScan) {
		this.swaggerScan = swaggerScan;
	}
}
