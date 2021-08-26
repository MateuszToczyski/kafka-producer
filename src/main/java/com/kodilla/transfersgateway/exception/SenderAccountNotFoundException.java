package com.kodilla.transfersgateway.exception;

public class SenderAccountNotFoundException extends RuntimeException {

    public SenderAccountNotFoundException(String message) {
        super(message);
    }
}
