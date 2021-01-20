package com.codecool.review.validation;

import com.codecool.review.validation.annotation.ValidMessage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MessageValidator implements ConstraintValidator<ValidMessage, String> {

    @Override
    public boolean isValid(String message, ConstraintValidatorContext context) {
        return containsValidChars(message);
    }

    public boolean containsValidChars(String message) {
        if (message != null) {
            String regex = "^[a-zA-Z0-9\\s.:,!?()]{1,255}$";
            return message.matches(regex);
        }
        return false;
    }
}
