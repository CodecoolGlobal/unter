package com.codecool.earthbnb.gateway.service.validation.annotation;


import com.codecool.earthbnb.gateway.service.validation.LongNotNullValidator;
import com.codecool.earthbnb.gateway.service.validation.NameValidator;

import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@javax.validation.Constraint(validatedBy = { NameValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface ValidName {

    String message() default "Name can only contain letters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
