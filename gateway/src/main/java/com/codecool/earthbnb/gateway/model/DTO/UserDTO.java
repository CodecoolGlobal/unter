package com.codecool.earthbnb.gateway.model.DTO;

import com.codecool.earthbnb.gateway.service.validation.annotation.ValidLocalDate;
import com.codecool.earthbnb.gateway.service.validation.annotation.ValidLong;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class UserDTO {

    private Long id;

    @NotEmpty(message = "First name is required")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    private String lastName;

    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    private LocalDateTime registrationDate;

    @ValidLocalDate
    private LocalDate birthDate;

}
