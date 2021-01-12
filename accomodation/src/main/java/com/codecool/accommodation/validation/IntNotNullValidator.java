package com.codecool.accommodation.validation;

import com.codecool.accommodation.validation.annotation.ValidInteger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntNotNullValidator implements ConstraintValidator<ValidInteger, Integer> {

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer != null;
    }
}
