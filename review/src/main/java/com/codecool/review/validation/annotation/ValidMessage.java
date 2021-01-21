package com.codecool.review.validation.annotation;

import com.codecool.review.validation.MessageValidator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { MessageValidator.class }) // we define the class that is going to validate our field
@Target({FIELD, CONSTRUCTOR, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidMessage {

    String message() default "Message is invalid."; // the error message
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
