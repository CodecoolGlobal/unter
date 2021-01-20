package com.codecool.review.validation;

import com.codecool.review.validation.annotation.ValidRating;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RatingValidator implements ConstraintValidator<ValidRating, Double> {

    @Override
    public boolean isValid(Double rating, ConstraintValidatorContext context) {
        return rating != null && isRatingBetweenOneAndFive(rating);
    }

    public boolean isRatingBetweenOneAndFive(Double rating) {
        return rating >= 0 && rating <= 5;
    }
}
