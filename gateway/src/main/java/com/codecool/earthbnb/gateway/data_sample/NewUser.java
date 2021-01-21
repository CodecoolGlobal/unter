package com.codecool.earthbnb.gateway.data_sample;

import com.codecool.earthbnb.gateway.model.entity.UserAddress;
import com.codecool.earthbnb.gateway.model.entity.types.Gender;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class NewUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime registrationDate;
    private LocalDate birthDate;
    private String phoneNumber;
    private Gender gender;
    private UserAddress address;
    private List<String> roles;

    public void createRoles() {
        this.roles = new ArrayList<>();
    }


}
