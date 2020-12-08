package com.codecool.accommodation.model.DTO;

import com.codecool.accommodation.model.entity.Host;
import com.codecool.accommodation.model.entity.Location;
import com.codecool.accommodation.model.entity.Room;
import com.codecool.accommodation.model.entity.types.AccommodationType;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AccommodationDTO {

    private Host host;
    private String name;
    private String description;
    private Location location;
    private AccommodationType type;
    private Integer maxNumberOfGuest;
    private Set<Room> rooms;

}
