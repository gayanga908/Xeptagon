package com.test.xeptagon.exceptions;

public class ClassNameAlreadyUsedException extends RuntimeException{
    public ClassNameAlreadyUsedException(String message) {
        super(message);
    }
}
