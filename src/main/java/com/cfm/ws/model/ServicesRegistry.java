package com.cfm.ws.model;

public class ServicesRegistry {

	private long callCounter;
	private long calledLastHour;
	private long calledLast24Hours;
	private String averageResponse;
	private String operationName;

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getAverageResponse() {
		return averageResponse;
	}

	public void setAverageResponse(String averageResponse) {
		this.averageResponse = averageResponse;
	}

	public long getCallCounter() {
		return callCounter;
	}

	public void setCallCounter(long callCounter) {
		this.callCounter = callCounter;
	}

	public long getCalledLastHour() {
		return calledLastHour;
	}

	public void setCalledLastHour(long calledLastHour) {
		this.calledLastHour = calledLastHour;
	}

	public long getCalledLast24Hours() {
		return calledLast24Hours;
	}

	public void setCalledLast24Hours(long calledLast24Hours) {
		this.calledLast24Hours = calledLast24Hours;
	}

}
