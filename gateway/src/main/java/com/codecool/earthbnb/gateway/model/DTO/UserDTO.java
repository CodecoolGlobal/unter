package com.codecool.earthbnb.gateway.model.DTO;

import com.codecool.earthbnb.gateway.service.validation.annotation.*;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class UserDTO {

    private Long id;

    //@NotEmpty(message = "First name is required")
    @ValidName
    private String firstName;

    //@NotEmpty(message = "Last name is required")
    @ValidName
    private String lastName;

    //@NotEmpty(message = "Email is required")
    @ValidEmailFormat
    private String email;

    //@NotEmpty(message = "Password is required")
    @ValidPassword
    private String password;

    private LocalDateTime registrationDate;

    @ValidLocalDate
    private LocalDate birthDate;

}
