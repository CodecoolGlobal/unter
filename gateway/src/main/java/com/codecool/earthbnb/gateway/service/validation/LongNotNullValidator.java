package com.codecool.earthbnb.gateway.service.validation;


import com.codecool.earthbnb.gateway.service.validation.annotation.ValidLong;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LongNotNullValidator implements ConstraintValidator<ValidLong, Long> {

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return aLong != null;
    }

}
