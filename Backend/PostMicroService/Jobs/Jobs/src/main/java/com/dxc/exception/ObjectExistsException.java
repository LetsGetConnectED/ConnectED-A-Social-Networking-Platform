package com.dxc.exception;

public class ObjectExistsException extends RuntimeException {
    public ObjectExistsException(String message) {
        super(message);
    }
}