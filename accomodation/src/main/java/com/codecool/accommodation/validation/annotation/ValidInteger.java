package com.codecool.accommodation.validation.annotation;

import com.codecool.accommodation.validation.IntNotNullValidator;

import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@javax.validation.Constraint(validatedBy = { IntNotNullValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface ValidInteger {

    String message() default "Input must be a number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
