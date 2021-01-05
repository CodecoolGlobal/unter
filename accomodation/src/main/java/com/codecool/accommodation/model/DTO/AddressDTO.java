package com.codecool.accommodation.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {

    private Integer houseNumber;
    private String street;
    private String city;
    private String zipCode;

}
