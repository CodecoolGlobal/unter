package com.codecool.accommodation.validation;

import com.codecool.accommodation.validation.annotation.ValidLong;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LongNotNullValidator implements ConstraintValidator<ValidLong, Long> {

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return aLong != null;
    }

}
