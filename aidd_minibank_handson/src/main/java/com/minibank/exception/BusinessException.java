package com.minibank.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
    private HttpStatus httpStatus;
    
    public BusinessException() {
        this("undefined", HttpStatus.EXPECTATION_FAILED);
    }

    public BusinessException(String message) {
        this(message, HttpStatus.EXPECTATION_FAILED);
    }

    public BusinessException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return this.message;
    }

    public HttpStatus getHttpStatus() {

        return httpStatus;
    }
}
