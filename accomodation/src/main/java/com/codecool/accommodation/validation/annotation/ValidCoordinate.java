package com.codecool.accommodation.validation.annotation;

import com.codecool.accommodation.validation.CoordinateNotNullValidator;
import com.codecool.accommodation.validation.LongNotNullValidator;

import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@javax.validation.Constraint(validatedBy = { CoordinateNotNullValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface ValidCoordinate {

    String message() default "Latitude and longitude can't be null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
