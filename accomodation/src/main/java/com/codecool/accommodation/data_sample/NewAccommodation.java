package com.codecool.accommodation.data_sample;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class NewAccommodation {
    private String city;
    private String street;
    private String zipCode;
    private int houseNumber;
    private double latitude;
    private double longitude;
    private String description;
    private int maxNumberOfGuests;
    private String name;
    private Long hostId;
    //private String picture;
    private List<String> pictures;
}
