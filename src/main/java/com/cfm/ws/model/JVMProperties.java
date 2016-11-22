package com.cfm.ws.model;

public class JVMProperties {

	private String currentMemoryUsage;
	private String osName;
	private String applicationName;
	private String javaRuntimeVersion;
	private String javaVersion;
	private String javaRuntimeName;
	private String javaVMName;
	private String javaSpecVersion;
	private String upTime;

	public String getUpTime() {
		return upTime;
	}

	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public String getCurrentMemoryUsage() {
		return currentMemoryUsage;
	}

	public void setCurrentMemoryUsage(String currentMemoryUsage) {
		this.currentMemoryUsage = currentMemoryUsage;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getJavaRuntimeVersion() {
		return javaRuntimeVersion;
	}

	public void setJavaRuntimeVersion(String javaRuntimeVersion) {
		this.javaRuntimeVersion = javaRuntimeVersion;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	public String getJavaRuntimeName() {
		return javaRuntimeName;
	}

	public void setJavaRuntimeName(String javaRuntimeName) {
		this.javaRuntimeName = javaRuntimeName;
	}

	public String getJavaVMName() {
		return javaVMName;
	}

	public void setJavaVMName(String javaVMName) {
		this.javaVMName = javaVMName;
	}

	public String getJavaSpecVersion() {
		return javaSpecVersion;
	}

	public void setJavaSpecVersion(String javaSpecVersion) {
		this.javaSpecVersion = javaSpecVersion;
	}

}
