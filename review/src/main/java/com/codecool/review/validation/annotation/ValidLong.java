package com.codecool.review.validation.annotation;

import com.codecool.review.validation.LongValidator;

import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@javax.validation.Constraint(validatedBy = { LongValidator.class })
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidLong {

    String message() default "Input must be a number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
