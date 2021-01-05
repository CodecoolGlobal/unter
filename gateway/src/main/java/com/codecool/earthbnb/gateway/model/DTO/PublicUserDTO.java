package com.codecool.earthbnb.gateway.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicUserDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String email;

}
