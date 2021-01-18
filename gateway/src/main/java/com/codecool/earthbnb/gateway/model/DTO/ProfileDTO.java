package com.codecool.earthbnb.gateway.model.DTO;

import com.codecool.earthbnb.gateway.model.entity.UserAddress;
import com.codecool.earthbnb.gateway.model.entity.types.Gender;
import com.codecool.earthbnb.gateway.service.validation.annotation.ValidEmailFormat;
import com.codecool.earthbnb.gateway.service.validation.annotation.ValidLocalDate;
import com.codecool.earthbnb.gateway.service.validation.annotation.ValidName;
import com.codecool.earthbnb.gateway.service.validation.annotation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDTO {

    private String fullName;

    private String email;

    private LocalDate birthDate;

    private UserAddress address;

    private Gender gender;

    private String phoneNumber;

}
