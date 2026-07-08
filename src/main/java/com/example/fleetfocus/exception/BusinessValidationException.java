package com.example.fleetfocus.exception;

public class BusinessValidationException extends RuntimeException {
    public BusinessValidationException(String msg){
        super(msg);
    }
}
