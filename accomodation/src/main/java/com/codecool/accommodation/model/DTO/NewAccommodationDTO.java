package com.codecool.accommodation.model.DTO;

import com.codecool.accommodation.model.entity.Address;
import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.model.entity.Room;
import com.codecool.accommodation.model.entity.types.AccommodationType;
import com.codecool.accommodation.validation.annotation.ValidAddress;
import com.codecool.accommodation.validation.annotation.ValidCoordinate;
import com.codecool.accommodation.validation.annotation.ValidInteger;
import com.codecool.accommodation.validation.annotation.ValidLong;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
public class NewAccommodationDTO {

    @ValidLong(message = "Host id can't be null")
    private Long hostId;

    @NotEmpty(message = "Name is required")
    private String name;

    private String description;

    @ValidCoordinate
    private Coordinate coordinate;

    private AccommodationType type;

    @ValidInteger(message = "Max num of guests can't be null")
    private Integer maxNumberOfGuest;

    private Set<Room> rooms;

    @ValidAddress
    private Address address;

}
