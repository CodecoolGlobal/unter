package com.codecool.accommodation.model.DTO;

import com.codecool.accommodation.model.entity.Coordinate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccommodationDTO {
    private Long id;
    private String accommodationName;
    private String pictures;
    private Integer capacity;
    private Integer numberOfRooms;
    private Integer numberOfBeds;
    private Integer numberOfBathrooms;
    private Coordinate coordinates;

//    private String description;
//    private AccommodationType type;
//    private Set<Room> rooms;
//    private Address address;
}
