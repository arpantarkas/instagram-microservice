package com.arpan.instagram.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message) {
        super("User with user-id "+message+ " not found");
    }

    public UserNotFoundException(String message, Throwable cause) {
        super("User with user-id "+message+ " not found", cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
