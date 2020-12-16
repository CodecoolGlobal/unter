package com.codecool.accommodation.service;

import com.codecool.accommodation.exception.NoDataFoundException;
import com.codecool.accommodation.model.DTO.NewAccommodationDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.service.DAO.AccommodationDAO;
import com.codecool.accommodation.service.DAO.RoomDAO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NullArgumentException;
import com.codecool.accommodation.model.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationDAO accommodationDAO;
    private final RoomDAO roomDAO;

    public List<Accommodation> getAllAccommodation(Long hostId) {
        if(accommodationDAO.findAllByHostId(hostId) == null){
            throw new NoDataFoundException();
        }
        return accommodationDAO.findAllByHostId(hostId);
    }

    public void saveNewAccommodation(NewAccommodationDTO newAccommodationDTO) {
        accommodationDAO.saveNewAccommodation(newAccommodationDTO);
    }

    public void deleteAccommodation(Long accommodationId) {
        if(accommodationDAO.isExisted(accommodationId)){
            accommodationDAO.deleteAccommodation(accommodationId);
        } else{
            throw new NoDataFoundException();
        }

    }

    public void updateAccommodation(String accommodationId, NewAccommodationDTO newAccommodationDTO) {
        try {
            accommodationDAO.updateAccommodation(Long.parseLong(accommodationId), newAccommodationDTO);
        } catch (NullArgumentException exception) {
            exception.printStackTrace();
        }
    }

    public List<Accommodation> findAll() {
        return accommodationDAO.findAll();
    }

    public Accommodation findAccommodationById(Long accommodationId) {

        return accommodationDAO.findAccommodationById(accommodationId);
    }
}
