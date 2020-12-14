package com.codecool.accommodation.service.DAO;

import com.codecool.accommodation.model.DTO.NewAccommodationDTO;
import com.codecool.accommodation.model.DTO.RoomDTO;

public interface RoomDAO {
    void saveNewRoom(RoomDTO roomDTO, Long accommodationId);
    void saveNewRoom(RoomDTO roomDTO, NewAccommodationDTO newAccommodationDTO);
}
