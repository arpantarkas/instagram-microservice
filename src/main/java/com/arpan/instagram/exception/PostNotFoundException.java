package com.arpan.instagram.exception;

import org.springframework.http.HttpStatus;

public class PostNotFoundException extends BaseException {
    public PostNotFoundException(String message) {
        super(message+ "Post not found");
    }

    public PostNotFoundException(String message, Throwable cause) {
        super(message+ "Post not found", cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
