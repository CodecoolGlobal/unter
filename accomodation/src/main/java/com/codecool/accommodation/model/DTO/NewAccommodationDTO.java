package com.codecool.accommodation.model.DTO;

import com.codecool.accommodation.model.entity.Address;
import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.model.entity.Room;
import com.codecool.accommodation.model.entity.types.AccommodationType;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class NewAccommodationDTO {

    private Long hostId;
    private String name;
    private String description;
    private Coordinate coordinate;
    private AccommodationType type;
    private Integer maxNumberOfGuest;
    private Set<Room> rooms;
    private Address address;

}
