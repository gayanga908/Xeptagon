package com.test.xeptagon.exceptions;

public class NotAllowedToCreateUserException extends RuntimeException {
        public NotAllowedToCreateUserException(String message) {
                super(message);
        }
}
