package com.codecool.accomodation.model.DTO;

import com.codecool.accomodation.model.entity.Host;
import com.codecool.accomodation.model.entity.Location;
import com.codecool.accomodation.model.entity.Room;
import com.codecool.accomodation.model.entity.types.AccommodationType;
import lombok.Data;

import java.util.Set;

@Data
public class AccommodationDTO {

    private Host host;
    private String name;
    private String description;
    private Location location;
    private AccommodationType type;
    private Integer maxNumberOfGuest;
    private Set<Room> rooms;

}
