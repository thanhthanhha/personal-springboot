package com.realchat.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.realchat.store.exception.BusinessException;
import com.realchat.store.exception.SystemException;
import com.realchat.store.exception.ErrorResponse;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex, WebRequest request) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.status(ex.getHttpStatus().value())
				.message(ex.getMessage())
				.path(request.getDescription(false))
				.timestamp(LocalDateTime.now())
				.build();
		
		return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
	}
	
	@ExceptionHandler(SystemException.class)
	public ResponseEntity<ErrorResponse> handleSystemException(SystemException ex, WebRequest request) {
	    HttpStatus status = (ex.getHttpStatus() != null) ? ex.getHttpStatus() : HttpStatus.INTERNAL_SERVER_ERROR;
	    
	    ErrorResponse errorResponse = ErrorResponse.builder()
	            .status(status.value())
	            .message(ex.getMessage())
	            .path(request.getDescription(false))
	            .timestamp(LocalDateTime.now())
	            .build();
	    
	    return new ResponseEntity<>(errorResponse, status);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
	    ErrorResponse errorResponse = ErrorResponse.builder()
	            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
	            .message(ex.getMessage())
	            .path(request.getDescription(false))
	            .timestamp(LocalDateTime.now())
	            .build();
	    
	    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
