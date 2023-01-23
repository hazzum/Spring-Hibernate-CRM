package com.hazzum.CRMDemo.rest.exceptionHandler;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

}
