package com.codecool.accomodation.model.DTO;

import com.codecool.accomodation.model.entity.Coordinate;
import com.codecool.accomodation.model.entity.Host;
import com.codecool.accomodation.model.entity.Location;
import com.codecool.accomodation.model.entity.Room;
import com.codecool.accomodation.model.entity.types.AccommodationType;
import lombok.Data;

import javax.persistence.PostLoad;
import java.util.Set;

@Data
public class AccommodationDTO {

    private Long id;
    private Integer maxNumberOfGuests;
    private String name;
    private String description;
    private String pictureUrl;

    private Integer numberOfRooms;
    private Integer numberOfBeds = 0;

    private Coordinate coordinates;

    private AccommodationType type;

    private Location location;
    private Set<Room> rooms;

   /* @PostLoad
    private void load() {
        setNumberOfRooms();
        setNumberOfBeds();
        setCoordinates();
    }

    public void setNumberOfRooms() {
        if (!rooms.isEmpty()) {
            this.numberOfRooms = rooms.size();
        }
    }

    public void setNumberOfBeds() {

        if (!rooms.isEmpty()) {
            for (Room room : rooms) {
                numberOfBeds +=
                        room.getBeds().values().stream().reduce(0, Integer::sum);
            }
        }
    }

    public void setCoordinates(){
        if(location != null){
           this.coordinates = location.getCoordinate();
        }
    };*/
}
