package com.test.xeptagon.exceptions;

public class UserCreationFailedException extends RuntimeException {
    public UserCreationFailedException(String message) {
        super(message);
    }
}
