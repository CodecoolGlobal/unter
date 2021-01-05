package com.codecool.accommodation.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDTO {
    private Long id;
    private String accommodationName;

    private String description;
    private String pictures;
    private Integer capacity;
    private Integer numberOfRooms;
    private Integer numberOfBeds;
    private Integer numberOfBathrooms;
    private CoordinateDTO coordinates;

    // TODO: implement these fields later
//    private Long hostId;
//    private AccommodationType type;
//    private Set<Room> rooms;
//    private Address address;
}
