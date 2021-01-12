package com.codecool.earthbnb.gateway.exception;

public class UserExistsException extends RuntimeException{

    public UserExistsException() {
        super("User already exists");
    }
}
