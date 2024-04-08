package com.dev.specification.exception;

import org.springframework.http.HttpStatus;

public class FilterIllegalArgumentException extends HTTPException {

    public FilterIllegalArgumentException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }
}

