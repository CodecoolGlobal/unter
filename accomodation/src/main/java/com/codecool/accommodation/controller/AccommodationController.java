package com.codecool.accommodation.controller;

import com.codecool.accommodation.model.DTO.NewAccommodationDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService service;

    @GetMapping
    public List<Accommodation> getAll(){
        return service.findAll();
    }
  
    // if there is no accommodation found by the given host id, it returns an empty list!
    @GetMapping("/host-id/{hostId}")
    public List<Accommodation> getAllAccommodationByHost(@PathVariable(name = "hostId") Long hostId, HttpServletResponse response) {
        return service.getAllAccommodation(hostId);
    }

    @PostMapping
    public void saveNewAccommodation(@RequestBody @Valid NewAccommodationDTO newAccommodationDTO) {
        service.saveNewAccommodation(newAccommodationDTO);
    }

    @DeleteMapping("/accommodation-id/{accommodationId}")
    public void deleteAccommodation(@PathVariable(name = "accommodationId") Long accommodationId, HttpServletResponse response) {
       service.deleteAccommodation(accommodationId);
    }

    @PutMapping("/accommodation-id/{accommodationId}")
    public void updateAccommodation(@PathVariable(name = "accommodationId") Long accommodationId, @RequestBody NewAccommodationDTO newAccommodationDTO) {
        service.updateAccommodation(accommodationId, newAccommodationDTO);
    }

    @GetMapping("/accommodation-id/{accommodationId}")
    public Accommodation getAccommodationById(@PathVariable(name = "accommodationId")Long accommodationId) {
        System.out.println("cica"+accommodationId);
        return service.findAccommodationById(accommodationId);
    }
}
