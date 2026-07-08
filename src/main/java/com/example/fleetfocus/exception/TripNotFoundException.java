package com.example.fleetfocus.exception;

public class TripNotFoundException extends RuntimeException{
    public TripNotFoundException(String msg){
        super(msg);
    }
}
