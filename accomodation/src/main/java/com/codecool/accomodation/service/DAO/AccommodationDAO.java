package com.codecool.accomodation.service.DAO;

import com.codecool.accomodation.model.DTO.AccommodationDTO;
import com.codecool.accomodation.model.entity.Accommodation;

import java.util.List;

public interface AccommodationDAO {

    List<Accommodation> findAllByHost(Long userId);
    void saveNewAccommodation(AccommodationDTO accommodationDTO);
    void deleteAccommodation(Long accommodationId);
    Accommodation findAccommodationById(Long accommodationId);
    void updateAccommodation(Long accommodationId, AccommodationDTO accommodationDTO);
}
