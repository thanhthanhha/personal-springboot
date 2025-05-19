package com.email.exception;

import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;


@Data
public class ErrorResponse {
    private int status;
    private String message;
    private String path;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    
    @Builder
    public ErrorResponse(int status, String message, String path, LocalDateTime timestamp) {
    	this.status = status;
    	this.message = message;
    	this.path = path;
    	this.timestamp = timestamp;
    }
}
