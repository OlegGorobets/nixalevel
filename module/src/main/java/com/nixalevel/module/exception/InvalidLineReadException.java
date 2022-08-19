package com.nixalevel.module.exception;

public class InvalidLineReadException extends RuntimeException {

    public InvalidLineReadException() {
    }

    public InvalidLineReadException(String message) {
        super(message);
    }
}
