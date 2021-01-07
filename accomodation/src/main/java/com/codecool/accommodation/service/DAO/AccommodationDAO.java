package com.codecool.accommodation.service.DAO;

import com.codecool.accommodation.model.DTO.NewAccommodationDTO;
import com.codecool.accommodation.model.DTO.ResponseAccDTO;
import com.codecool.accommodation.model.entity.Accommodation;

import java.util.List;

public interface AccommodationDAO {

    List<Accommodation> findAll();
    List<Accommodation> findAllByHostId(Long hostId);
    void saveNewAccommodation(NewAccommodationDTO newAccommodationDTO);
    void deleteAccommodation(Long accommodationId);
    ResponseAccDTO findAccommodationById(Long accommodationId);
    void updateAccommodation(Long accommodationId, NewAccommodationDTO newAccommodationDTO);
    boolean isExisted(Long accommodationId);
}
