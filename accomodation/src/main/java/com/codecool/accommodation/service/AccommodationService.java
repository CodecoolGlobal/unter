package com.codecool.accommodation.service;

import com.codecool.accommodation.model.DTO.AccommodationDTO;
import com.codecool.accommodation.model.DTO.RoomDTO;
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
        return accommodationDAO.findAllByHostId(hostId);
    }

    public Response saveNewAccommodation(AccommodationDTO accommodationDTO) {
        try {
            if (accommodationDTO.getHostId() != null ||
                accommodationDTO.getName() != null ||
                accommodationDTO.getMaxNumberOfGuest() != null) {

                accommodationDAO.saveNewAccommodation(accommodationDTO);


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
        if(accommodationDAO.isExisted(accommodationId)){
            try {
                accommodationDAO.deleteAccommodation(accommodationId);
            } catch (NoSuchElementException exception) {
                exception.printStackTrace();
                return true;
            }
            return accommodationDAO.isExisted(accommodationId);
        } else{
            return true;
        }

    }

    public void updateAccommodation(String accommodationId, AccommodationDTO accommodationDTO) {
        try {
            accommodationDAO.updateAccommodation(Long.parseLong(accommodationId), accommodationDTO);
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
