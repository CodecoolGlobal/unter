package com.codecool.accommodation.validation.annotation;


import com.codecool.accommodation.validation.AddressNotNullValidator;

import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@javax.validation.Constraint(validatedBy = { AddressNotNullValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface ValidAddress {
    String message() default "Address fields must not be null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
