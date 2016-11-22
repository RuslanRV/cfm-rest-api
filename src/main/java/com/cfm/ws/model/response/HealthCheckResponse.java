package com.cfm.ws.model.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.cfm.ws.model.AppProperties;
import com.cfm.ws.model.CurrentLogger;
import com.cfm.ws.model.DBConnection;
import com.cfm.ws.model.JVMProperties;

@XmlRootElement
public class HealthCheckResponse extends ResponseEntity {

	/** GREEN, YELLOW, RED */
	private String status;

	public static enum STATUS {
		GREEN, YELLOW, RED;
	}

	private DBConnection dbConnection;
	private JVMProperties jvm;
	private AppProperties appProperties;
	private List<CurrentLogger> currentLoggers;

	public void addLoggers(final List<CurrentLogger> loggers) {
		if (null == currentLoggers) {
			currentLoggers = new ArrayList<CurrentLogger>();
		}
		currentLoggers.addAll(loggers);
	}

	public void addLogger(final CurrentLogger logger) {
		if (null == currentLoggers) {
			currentLoggers = new ArrayList<CurrentLogger>();
		}
		currentLoggers.add(logger);
	}

	public AppProperties getAppProperties() {
		return appProperties;
	}

	public void setAppProperties(AppProperties appProperties) {
		this.appProperties = appProperties;
	}

	public List<CurrentLogger> getCurrentLoggers() {
		return currentLoggers;
	}

	public void setCurrentLoggers(List<CurrentLogger> currentLoggers) {
		this.currentLoggers = currentLoggers;
	}

	public JVMProperties getJvm() {
		return jvm;
	}

	public void setJvm(JVMProperties jvm) {
		this.jvm = jvm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DBConnection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}