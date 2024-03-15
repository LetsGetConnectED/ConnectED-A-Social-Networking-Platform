package com.dxc.exception;

<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
=======
public class IncorrectPasswordException {

>>>>>>> 9803b956d5e3410726c9811648a72b8ca5376f2e
}
