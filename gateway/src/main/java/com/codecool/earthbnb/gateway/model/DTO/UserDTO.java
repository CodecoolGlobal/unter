package com.codecool.earthbnb.gateway.model.DTO;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import java.time.LocalDateTime;


@Data
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private LocalDateTime registrationDate;

}
