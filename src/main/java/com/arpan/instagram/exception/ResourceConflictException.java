package com.arpan.instagram.exception;

import org.springframework.http.HttpStatus;

public class ResourceConflictException extends BaseException {

    public ResourceConflictException(String message) {
        super(message);
    }

    public ResourceConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
