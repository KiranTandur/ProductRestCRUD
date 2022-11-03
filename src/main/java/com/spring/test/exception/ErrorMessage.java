package com.spring.test.exception;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessage extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public ErrorMessage(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ErrorMessage() {
		
	}

}