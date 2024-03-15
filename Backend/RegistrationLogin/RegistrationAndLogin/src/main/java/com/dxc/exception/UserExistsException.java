package com.dxc.exception;

<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserExistsException extends RuntimeException {
    public UserExistsException(String message) {
        super(message);
    }
=======
public class UserExistsException {

>>>>>>> 9803b956d5e3410726c9811648a72b8ca5376f2e
}
