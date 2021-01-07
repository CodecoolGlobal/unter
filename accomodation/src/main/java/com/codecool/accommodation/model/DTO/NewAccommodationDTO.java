package com.codecool.accommodation.model.DTO;

import com.codecool.accommodation.model.entity.Address;
import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.model.entity.Room;
import com.codecool.accommodation.model.entity.types.AccommodationType;
import com.codecool.accommodation.validation.annotation.ValidAddress;
import com.codecool.accommodation.validation.annotation.ValidCoordinate;
import com.codecool.accommodation.validation.annotation.ValidInteger;
import com.codecool.accommodation.validation.annotation.ValidLong;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewAccommodationDTO {

    @ValidLong(message = "Host id can't be null")
    private Long hostId;

    @NotEmpty(message = "Name is required")
    private String name;

    private String description;

    private List<String> pictures;

    @ValidCoordinate
    private Coordinate coordinate;

    private AccommodationType type;

    @ValidInteger(message = "Max num of guests can't be null")
    private Integer maxNumberOfGuest;

    private Set<Room> rooms;

    @ValidAddress
    private Address address;

    void setCoordinates(Coordinate coordinates){
        coordinate = coordinates;
    }


}
