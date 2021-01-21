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
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAccDTO {

    private Long id;

    private Long hostId;

    private String name;

    private String description;

    private List<String> pictures;

    private Coordinate coordinate;

    private AccommodationType type;

    private Integer maxNumberOfGuest;

    private List<Room> rooms;

    private Address address;

    private Double rating;

    private List<ReviewResponseDTO> reviews;


    public void setMyCoordinates(Coordinate coordinates){
        coordinate = coordinates;
    }


}