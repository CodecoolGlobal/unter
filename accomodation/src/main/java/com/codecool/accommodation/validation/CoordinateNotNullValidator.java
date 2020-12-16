package com.codecool.accommodation.validation;

import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.validation.annotation.ValidCoordinate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CoordinateNotNullValidator implements ConstraintValidator<ValidCoordinate, Coordinate> {

    @Override
    public boolean isValid(Coordinate coordinate, ConstraintValidatorContext constraintValidatorContext) {
        return coordinate.getLatitude() != null && coordinate.getLongitude() != null;
    }

}
