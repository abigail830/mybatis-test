package com.github.abigail830.mybatictest.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomizeException extends ResponseStatusException {


    public CustomizeException(HttpStatus status) {
        super(status);
    }

    public CustomizeException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public CustomizeException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
