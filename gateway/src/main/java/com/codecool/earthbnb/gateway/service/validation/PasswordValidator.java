package com.codecool.earthbnb.gateway.service.validation;

import com.codecool.earthbnb.gateway.service.validation.annotation.ValidLocalDate;
import com.codecool.earthbnb.gateway.service.validation.annotation.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return passwordValid(value);
    }

    public static boolean passwordValid(String password){
        if(password != null){
            String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,20}$";
            return password.matches(regex);
        }
        return false;
    }
}
