package com.nixalevel.module.exception;

public class InvalidProductException extends RuntimeException{

    public InvalidProductException() {
    }

    public InvalidProductException(String message) {
        super(message);
    }
}
