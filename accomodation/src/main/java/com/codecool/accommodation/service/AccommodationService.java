package com.codecool.accommodation.service;

import com.codecool.accommodation.model.DTO.AccommodationDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.service.DAO.AccommodationDAO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NullArgumentException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationDAO dao;

    public List<Accommodation> getAllAccommodation(String hostId) {
        if (hostId == null)
            return null;
        return dao.findAllByHost(Long.parseLong(hostId));
    }

    public void saveNewAccommodation(AccommodationDTO accommodationDTO) throws NullArgumentException {
        try {
            dao.saveNewAccommodation(accommodationDTO);
            if (accommodationDTO.getMaxNumberOfGuests() == null || accommodationDTO.getName() == null) {
                throw new NullArgumentException("Field cannot be null!!!");
            }
        } catch (NullArgumentException exception) {
            exception.printStackTrace();
        }
    }

    public void deleteAccommodation(String accommodationId) throws NullPointerException {
        try{
            dao.deleteAccommodation(Long.parseLong(accommodationId));
        }catch (NullPointerException exception){
            exception.printStackTrace();
        }
    }

    public void updateAccommodation(String accommodationId, AccommodationDTO accommodationDTO) {
        try {
            dao.updateAccommodation(Long.parseLong(accommodationId), accommodationDTO);
        } catch (NullArgumentException exception) {
            exception.printStackTrace();
        }
    }

    public List<Accommodation> findAll() {
        return dao.findAll();
    }
}
