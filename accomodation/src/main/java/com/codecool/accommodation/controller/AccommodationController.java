package com.codecool.accommodation.controller;

import com.codecool.accommodation.model.DTO.NewAccommodationDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService service;

    @GetMapping
    public List<Accommodation> getAll(){
        return service.findAll();
    }

    @GetMapping("/{hostId}")
    public List<Accommodation> getAllAccommodationByHost(@PathVariable(name = "hostId") Long hostId, HttpServletResponse response) {
        List<Accommodation> accommodations = service.getAllAccommodation(hostId);

        if (accommodations == null) {
            response.setStatus(401);
            try {
                response.getWriter().println("Invalid host id.");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return accommodations;
    }

    @PostMapping
    public void saveNewAccommodation(@RequestBody NewAccommodationDTO newAccommodationDTO) {
        service.saveNewAccommodation(newAccommodationDTO);
    }

    @DeleteMapping("/{accommodationId}")
    public void deleteAccommodation(@PathVariable(name = "accommodationId") Long accommodationId, HttpServletResponse response) {
        boolean notDeleted = service.deleteAccommodation(accommodationId);
        if (notDeleted) {
            response.setStatus(401);
        }
    }

    @PutMapping("/{accommodationId}")
    public void updateAccommodation(@PathVariable(name = "accommodationId") String accommodationId, @RequestBody NewAccommodationDTO newAccommodationDTO) {
        service.updateAccommodation(accommodationId, newAccommodationDTO);
    }

    @GetMapping("/get-accommodation/{accommodationId}")
    public Accommodation getAccommodationById(@PathVariable(name = "accommodationId")Long accommodationId) {
        return service.findAccommodationById(accommodationId);
    }
}