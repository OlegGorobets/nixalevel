package com.nixalevel.module.exception;

public class InvalidColumnHeadersException extends RuntimeException{

    public InvalidColumnHeadersException() {
    }

    public InvalidColumnHeadersException(String message) {
        super(message);
    }
}
