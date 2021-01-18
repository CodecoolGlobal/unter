package com.codecool.earthbnb.gateway.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAddressDTO {
    private String houseNumber;
    private String street;
    private String city;
    private String zipCode;
    private String country;
}
