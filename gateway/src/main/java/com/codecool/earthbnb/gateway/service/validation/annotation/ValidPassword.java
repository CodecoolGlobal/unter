package com.codecool.earthbnb.gateway.service.validation.annotation;

import com.codecool.earthbnb.gateway.service.validation.LocalDateNotNullValidator;
import com.codecool.earthbnb.gateway.service.validation.PasswordValidator;

import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@javax.validation.Constraint(validatedBy = { PasswordValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface ValidPassword {

    String message() default "Password must contain at least 1 capital letter and a special character and has to be at least 6 char long";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
