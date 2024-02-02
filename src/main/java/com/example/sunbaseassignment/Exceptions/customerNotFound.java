package com.example.sunbaseassignment.Exceptions;

public class customerNotFound extends RuntimeException{

    public customerNotFound(String message){
        super(message);
    }
}
