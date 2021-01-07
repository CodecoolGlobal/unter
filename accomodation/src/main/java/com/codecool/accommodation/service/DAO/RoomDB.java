package com.codecool.accommodation.service.DAO;

import com.codecool.accommodation.model.DTO.NewAccommodationDTO;
import com.codecool.accommodation.model.DTO.RoomDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.model.entity.Room;
import com.codecool.accommodation.repository.RoomRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class RoomDB implements RoomDAO{

    private AccommodationDAO accommodationDAO;

    private RoomRepository roomRepository;



    @Override
    public void saveNewRoom(RoomDTO roomDTO, NewAccommodationDTO newAccommodationDTO) {

    }
}
