package com.codecool.accommodation.service.DAO;

import com.codecool.accommodation.model.DTO.AccommodationDTO;
import com.codecool.accommodation.model.entity.Accommodation;

import java.util.List;

public interface AccommodationDAO {

    List<Accommodation> findAll();
    List<Accommodation> findAllByHost(Long userId);
    void saveNewAccommodation(AccommodationDTO accommodationDTO);
    void deleteAccommodation(Long accommodationId);
    Accommodation findAccommodationById(Long accommodationId);
    void updateAccommodation(Long accommodationId, AccommodationDTO accommodationDTO);
}
