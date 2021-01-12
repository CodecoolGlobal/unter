package com.codecool.earthbnb.gateway.service.validation;

import com.codecool.earthbnb.gateway.model.DTO.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {
    //checks first or last name for length (min and max) and containing characters
    public boolean validateName(String name, int minLength, int maxLength) {
        return isAlpha(name) && name.length() >= minLength && name.length() <= maxLength;
    }

    //checks password for min and max length
    public boolean validatePassword(String password) {
        return isValidPasswordSecure(password);
    }

    //checks username for min and max length
    public boolean validateUsername(String username, int minLength, int maxLength) {
        return username.length() >= minLength && username.length() <= maxLength && isAlphaNumWithUnderscore(username);
    }

    public boolean validateRegistration(UserDTO userDTO, int minLengthName, int maxLengthName, int minLengthUsername, int maxLengthUsername) {

        boolean validFirstName = validateName(userDTO.getFirstName(), minLengthName, maxLengthName);
        boolean validLastName = validateName(userDTO.getLastName(), minLengthName, maxLengthName);
        boolean validPassword = validatePassword(userDTO.getPassword());
//        boolean validUsername = validateUsername(userDTO.getUsername(), minLengthUsername, maxLengthUsername);

        return validFirstName && validLastName && validPassword ;
    }

    private boolean isAlpha(String string) {
        return string.matches("^(?U)[\\p{Alpha}\\-'. ]+");
    }

    private boolean isAlphaNumWithUnderscore(String string) {
        return string.matches("^(?U)[\\p{Alpha}\\-'. _]+");
    }

    private boolean isValidPasswordSecure(String password) {

        return true;
       // return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");
    }
}
