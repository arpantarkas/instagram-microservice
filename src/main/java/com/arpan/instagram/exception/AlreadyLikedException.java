package com.arpan.instagram.exception;

import org.springframework.http.HttpStatus;

public class AlreadyLikedException extends BaseException {
    public AlreadyLikedException(String message) {
        super(message);
    }

    public AlreadyLikedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
