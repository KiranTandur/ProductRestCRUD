package com.spring.test.exception;

public class DataNotFoundExceptionNew extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataNotFoundExceptionNew(String message) {
		super(message);
		System.out.println("inside constructor");
	}

	
}
