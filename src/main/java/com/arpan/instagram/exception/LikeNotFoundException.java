package com.arpan.instagram.exception;

import org.springframework.http.HttpStatus;

public class LikeNotFoundException extends BaseException {
    public LikeNotFoundException(String message) {
        super(message+" Like not found");
    }

    public LikeNotFoundException(String message, Throwable cause) {
        super(message+" Like not found", cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
