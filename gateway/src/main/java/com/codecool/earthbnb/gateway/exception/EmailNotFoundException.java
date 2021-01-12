package com.codecool.earthbnb.gateway.exception;

public class EmailNotFoundException extends RuntimeException{

    public EmailNotFoundException(String email){
        super(String.format("User with email %d not found", email));
    }
}
