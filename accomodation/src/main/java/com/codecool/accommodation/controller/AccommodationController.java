package com.codecool.accommodation.controller;

import com.codecool.accommodation.model.DTO.NewAccommodationDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    // if there is no accommodations found with that host id, it returns an empty list!
    @GetMapping("/host/{hostId}")
    public List<Accommodation> getAllAccommodationByHost(@PathVariable(name = "hostId") Long hostId, HttpServletResponse response) {
        return service.getAllAccommodation(hostId);
    }

    @PostMapping
    public void saveNewAccommodation(@RequestBody @Valid NewAccommodationDTO newAccommodationDTO) {
        service.saveNewAccommodation(newAccommodationDTO);
    }

    @DeleteMapping("/{accommodationId}")
    public void deleteAccommodation(@PathVariable(name = "accommodationId") Long accommodationId, HttpServletResponse response) {
       service.deleteAccommodation(accommodationId);
    }

    @PutMapping("/{accommodationId}")
    public void updateAccommodation(@PathVariable(name = "accommodationId") String accommodationId, @RequestBody NewAccommodationDTO newAccommodationDTO) {
        service.updateAccommodation(accommodationId, newAccommodationDTO);
    }

    @GetMapping("/{accommodationId}")
    public Accommodation getAccommodationById(@PathVariable(name = "accommodationId")Long accommodationId) {
        return service.findAccommodationById(accommodationId);
    }
}
