package com.codecool.earthbnb.gateway.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicUserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;

}
