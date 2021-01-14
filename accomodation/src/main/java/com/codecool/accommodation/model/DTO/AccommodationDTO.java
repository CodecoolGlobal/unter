package com.codecool.accommodation.model.DTO;

import com.codecool.accommodation.model.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDTO {
    private Long id;
    private String accommodationName;

    private String description;
    //private String pictures;
    private List<String> pictures;
    private Integer capacity;
    private Integer numberOfRooms;
    private Integer numberOfBeds;
    private Integer numberOfBathrooms;
    private CoordinateDTO coordinates;
    private Long hostId;
    private Address address;


    // TODO: implement these fields later
//    private AccommodationType type;
//    private Set<Room> rooms;
}
