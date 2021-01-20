package com.codecool.review.validation;

import com.codecool.review.validation.annotation.ValidLong;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LongValidator implements ConstraintValidator<ValidLong, Long> {

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return aLong != null;
    }

}
