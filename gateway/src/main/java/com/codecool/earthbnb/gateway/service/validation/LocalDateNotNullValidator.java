package com.codecool.earthbnb.gateway.service.validation;

import com.codecool.earthbnb.gateway.service.validation.annotation.ValidLocalDate;
import com.codecool.earthbnb.gateway.service.validation.annotation.ValidLong;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class LocalDateNotNullValidator implements ConstraintValidator<ValidLocalDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value != null;
    }
}
