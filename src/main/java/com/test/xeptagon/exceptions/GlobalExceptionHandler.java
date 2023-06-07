package com.test.xeptagon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotAllowedToCreateUserException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleNotAllowedToCreateUserException(
            NotAllowedToCreateUserException exception
    ) {
        return new ResponseEntity<>(exception.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UserCreationFailedException.class)
    public ResponseEntity<Object> handleUserCreationFailedException(
            UserCreationFailedException exception
    ) {
        return new ResponseEntity<>(exception.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleUserAlreadyExistException(
            UserAlreadyExistException exception
    ) {
        return new ResponseEntity<>(exception.getMessage(),
                HttpStatus.CONFLICT);
    }


    @ExceptionHandler(ClassCreationFailedException.class)
    public ResponseEntity<Object> handleClassCreationFailedException(
            ClassCreationFailedException exception
    ) {
        return new ResponseEntity<>(exception.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ClassNameAlreadyUsedException.class)
    public ResponseEntity<Object> handleClassNameAlreadyUsedException(
            ClassNameAlreadyUsedException exception
    ) {
        return new ResponseEntity<>(exception.getMessage(),
                HttpStatus.CONFLICT);
    }

}
