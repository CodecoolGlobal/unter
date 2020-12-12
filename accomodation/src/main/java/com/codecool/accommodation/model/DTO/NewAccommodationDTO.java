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
/*
Wrapper (List<AccomodationDTO>) {
id:id,
accommodation_name:accommodation_name
pictures:folder_url,
capacity:guest_number,
number_of_rooms:number_of_rooms,
number_of_beds:number_of_beds,
number_of_bathrooms:number_of_bathrooms,
coordinates:
  {
    latitude:latitude_coordinate,
    longitude:longitude_coordinate
  }
}
 */