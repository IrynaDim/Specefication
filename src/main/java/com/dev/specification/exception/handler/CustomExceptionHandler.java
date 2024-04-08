package com.dev.specification.exception.handler;

import com.dev.specification.exception.HTTPException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = HTTPException.class)
    public ResponseEntity<Object> handleHttpException(HTTPException exception, WebRequest request) {
        return buildExceptionBody(exception.getMessage(), HttpStatus.valueOf(exception.getCode()), request);
    }

    private ResponseEntity<Object> buildExceptionBody(String message, HttpStatus httpStatus, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(httpStatus.value());
        exceptionResponse.setMessage(message);
        exceptionResponse.setTimeStamp(LocalDateTime.now().toString());
        exceptionResponse.setUrl(request.getDescription(false).replace("uri=", ""));
        return ResponseEntity
                .status(httpStatus)
                .body(exceptionResponse);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExceptionResponse {
        private int status;
        private String message;
        private String timeStamp;
        private String url;
    }
}
