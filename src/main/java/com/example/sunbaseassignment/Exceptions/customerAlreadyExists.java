package com.example.sunbaseassignment.Exceptions;

public class customerAlreadyExists extends RuntimeException{
    public customerAlreadyExists(String message){
        super(message);
    }
}
