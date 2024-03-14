package com.dxc.exception;

<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
=======
public class UserNotFoundException {

>>>>>>> 9803b956d5e3410726c9811648a72b8ca5376f2e
}
