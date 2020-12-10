package com.codecool.accommodation.model.DTO;

import com.codecool.accommodation.model.entity.Accommodation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToOne;

@Data
@Builder
public class AddressDTO {

    private Integer houseNumber;
    private String street;
    private String city;
    private String zipCode;

}
