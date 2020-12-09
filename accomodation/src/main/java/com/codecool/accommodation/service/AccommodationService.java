package com.codecool.accommodation.service;

import com.codecool.accommodation.model.DTO.AccommodationDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.service.DAO.AccommodationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationDAO dao;

    public List<Accommodation> getAllAccommodation(String hostId) {
        return dao.findAllByHost(Long.parseLong(hostId));
    }

    public Accommodation findAccommodationById(Long id) {
        return dao.findAccommodationById(id);
    }

    public void saveNewAccommodation(AccommodationDTO accommodationDTO) {
        dao.saveNewAccommodation(accommodationDTO);
    }

    public void deleteAccommodation(String accommodationId) {
        dao.deleteAccommodation(Long.parseLong(accommodationId));
    }

    public void updateAccommodation(String accommodationId, AccommodationDTO accommodationDTO) {
        dao.updateAccommodation(Long.parseLong(accommodationId), accommodationDTO);
    }
}
