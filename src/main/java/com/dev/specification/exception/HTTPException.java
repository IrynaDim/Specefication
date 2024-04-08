package com.dev.specification.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class HTTPException extends RuntimeException {
    private final int code;

    public HTTPException(String message, int code) {
        super(message);
        this.code = code;
    }

    public HTTPException(String message) {
        super(message);
        this.code = HttpStatus.FORBIDDEN.value();
    }
}