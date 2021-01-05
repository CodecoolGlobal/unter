package com.codecool.accommodation.exception;

public class AccommodationNotFoundException extends RuntimeException{

    public AccommodationNotFoundException(Long accommodationId){
        super(String.format("Accommodation with Id %d not found", accommodationId));
    }
}
