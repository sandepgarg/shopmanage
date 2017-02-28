package com.sg.sm.to.common;


public class Error {
	public static final int VERSION = 1;
	private int statusCode;
	private int errorCode;
	private String message;
	private String remediation;

	public Error(final int statusCode, final int errorCode,
			final String message) {
		this(statusCode, errorCode, message, null);
	}

	public Error(final int statusCode, final int errorCode,
			final String message, final String remediation) {
		setStatusCode(statusCode);
		setErrorCode(errorCode);
		setMessage(message);
		setRemediation(remediation);
	}

	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRemediation() {
		return remediation;
	}

	public void setRemediation(String remediation) {
		this.remediation = remediation;
	}

	public static int getVersion() {
		return VERSION;
	}
}
