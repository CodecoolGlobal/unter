package com.codecool.earthbnb.gateway.exception;

public class UserNotExistsException extends RuntimeException{

    public UserNotExistsException() {
        super("User doesn't exist");
    }
}
