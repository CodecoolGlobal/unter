package com.codecool.earthbnb.gateway.service.validation.annotation;


import com.codecool.earthbnb.gateway.service.validation.LongNotNullValidator;

import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@javax.validation.Constraint(validatedBy = { LongNotNullValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface ValidLong {

    String message() default "Input must be a number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
