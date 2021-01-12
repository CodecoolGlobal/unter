package com.codecool.accommodation.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {

    private String houseNumber;
    private String street;
    private String city;
    private String zipCode;
    private String country;
}
