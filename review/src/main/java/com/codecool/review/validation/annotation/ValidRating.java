package com.codecool.review.validation.annotation;

import com.codecool.review.validation.RatingValidator;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RatingValidator.class) // we defined the class that is going to validate our field
@Target({CONSTRUCTOR, FIELD, METHOD, PARAMETER})
@Retention(RUNTIME)
public @interface ValidRating {

    String message() default "Rating value should be between 1 and 5 included"; // the error message
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
