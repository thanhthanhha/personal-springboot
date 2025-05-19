package com.email.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final HttpStatus httpStatus;
	
    public BusinessException() {
        this("undefined", HttpStatus.EXPECTATION_FAILED);
    }
    
    public BusinessException(String message) {
        this(message, HttpStatus.EXPECTATION_FAILED);
    }

    
    public BusinessException(String message, HttpStatus httpStatus) {
    	super(message);
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return super.getMessage(); // Use parent's message
    }
    
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
