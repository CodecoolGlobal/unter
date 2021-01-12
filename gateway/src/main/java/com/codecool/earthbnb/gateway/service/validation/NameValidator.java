package com.codecool.earthbnb.gateway.service.validation;

import com.codecool.earthbnb.gateway.service.validation.annotation.ValidLocalDate;
import com.codecool.earthbnb.gateway.service.validation.annotation.ValidName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class NameValidator implements ConstraintValidator<ValidName, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return nameValid(value);
    }

    public static boolean nameValid(String name){
        if(name != null){
            String regex = "^(?U)[\\p{Alpha}\\-'. ]+";
            return name.matches(regex);
        }
        return false;
    }
}
