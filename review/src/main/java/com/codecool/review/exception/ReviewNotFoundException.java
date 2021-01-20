package com.codecool.review.exception;

public class ReviewNotFoundException extends RuntimeException {

    public ReviewNotFoundException(Long reviewId) {
        super(String.format("Review with Id %d not found", reviewId));
    }
}
