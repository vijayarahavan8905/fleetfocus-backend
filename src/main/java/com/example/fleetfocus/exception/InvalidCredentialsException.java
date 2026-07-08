package com.example.fleetfocus.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String msg){
        super(msg);
    }
}
