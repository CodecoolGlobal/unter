package com.codecool.accommodation.service;

import com.codecool.accommodation.model.DTO.AccommodationDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.service.DAO.AccommodationDAO;
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

    private final AccommodationDAO dao;

    public List<Accommodation> getAllAccommodation(Long hostId) {
        return dao.findAllByHostId(hostId);
    }

    public Response saveNewAccommodation(AccommodationDTO accommodationDTO) {
        try {
            if (accommodationDTO.getHostId() != null ||
                accommodationDTO.getName() != null ||
                accommodationDTO.getMaxNumberOfGuest() != null) {

                dao.saveNewAccommodation(accommodationDTO);
                return new Response(true, "SUCCESS");

            } else {
                return new Response(false, "FAIL");
            }
        } catch (DataIntegrityViolationException exception) {
            exception.printStackTrace();
            return new Response(false, "FAIL");
        }
    }

    public boolean deleteAccommodation(Long accommodationId) {
        try {
            dao.deleteAccommodation(accommodationId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            return true;
        }
        return dao.isExisted(accommodationId);
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

    public Accommodation findAccommodationById(Long accommodationId) {
        return dao.findAccommodationById(accommodationId);
    }
}
