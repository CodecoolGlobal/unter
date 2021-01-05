package com.codecool.accommodation.validation;

import com.codecool.accommodation.model.entity.Address;
import com.codecool.accommodation.validation.annotation.ValidAddress;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressNotNullValidator implements ConstraintValidator<ValidAddress, Address> {
    @Override
    public boolean isValid(Address address, ConstraintValidatorContext constraintValidatorContext) {
       return onlyLetters(address.getCity()) && address.getHouseNumber() != null && address.getStreet() != null && address.getZipCode() != null;
        // return onlyLetters(address.getCity()) && address.getHouseNumber() != null &&  lettersAndNums(address.getStreet()) && lettersAndNums(address.getZipCode());
    }

    public boolean onlyLetters(String city){
        if(city != null){
            String regex = "^[a-zA-Zs+]*$";
            return city.matches(regex);
        }
        return false;
    }

    public boolean lettersAndNums(String word){
        if(word != null){
            String regex = "^[A-Za-z0-9_-]*$";
            return word.matches(regex);
        }
        return false;
    }
}
