package com.zosh.exception;

import com.zosh.payload.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // If any method or controller throws an exception of type Exception,
    // call this handler method instead of crashing or returning a default error.
    public ResponseEntity<ExceptionResponse> ExceptionHandler(Exception ex,
                                                              WebRequest webRequest){
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(),
                webRequest.getDescription(false), LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}
