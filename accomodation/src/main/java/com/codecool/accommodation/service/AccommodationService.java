package com.codecool.accommodation.service;

import com.codecool.accommodation.exception.NoDataFoundException;
import com.codecool.accommodation.model.DTO.NewAccommodationDTO;
import com.codecool.accommodation.model.DTO.ResponseAccDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.service.DAO.AccommodationDAO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NullArgumentException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationDAO accommodationDAO;

    public List<Accommodation> getAllAccommodation(Long hostId) {
        if (accommodationDAO.findAllByHostId(hostId) == null) {
            throw new NoDataFoundException();
        }
        return accommodationDAO.findAllByHostId(hostId);
    }

    public void saveNewAccommodation(NewAccommodationDTO newAccommodationDTO) {
        accommodationDAO.saveNewAccommodation(newAccommodationDTO);
    }

    public void deleteAccommodation(Long accommodationId) {
        if (accommodationDAO.isExisted(accommodationId)){
            accommodationDAO.deleteAccommodation(accommodationId);
        } else {
            throw new NoDataFoundException();
        }

    }

    public void updateAccommodation(Long accommodationId, NewAccommodationDTO newAccommodationDTO) {
        try {
            accommodationDAO.updateAccommodation(accommodationId, newAccommodationDTO);
        } catch (NullArgumentException exception) {
            exception.printStackTrace();
        }
    }

    public List<Accommodation> findAll() {
        return accommodationDAO.findAll();
    }

    public ResponseAccDTO findAccommodationById(Long accommodationId) {
        return accommodationDAO.findAccommodationById(accommodationId);
    }

    public List<String> findAllAccommodationTypes() {
        return accommodationDAO.findAllAccommodationTypes();
    }

    public List<String> findAllRoomTypes() {
        return accommodationDAO.findAllRoomTypes();
    }

    public List<String> findAllBedTypes() {
        return accommodationDAO.findAllBedTypes();
    }
}
