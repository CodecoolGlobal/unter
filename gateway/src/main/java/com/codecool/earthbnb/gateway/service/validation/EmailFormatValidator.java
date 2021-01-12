package com.codecool.earthbnb.gateway.service.validation;

import com.codecool.earthbnb.gateway.service.validation.annotation.ValidEmailFormat;
import com.codecool.earthbnb.gateway.service.validation.annotation.ValidLocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class EmailFormatValidator implements ConstraintValidator<ValidEmailFormat, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return emailValid(email);
    }

    public static boolean emailValid(String email){
        if(email != null){
            String regex = "^(.+)@(.+)$";
            return email.matches(regex);
        }
        return false;
    }
}
