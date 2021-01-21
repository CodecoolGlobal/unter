package com.codecool.accommodation.controller;

import com.codecool.accommodation.model.DTO.NewAccommodationDTO;
import com.codecool.accommodation.model.DTO.ResponseAccDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.model.wrapper.AccommodationDTOWrapper;
import com.codecool.accommodation.service.AccommodationService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    public AccommodationDTOWrapper getAllAccommodationByHost(@PathVariable(name = "hostId") Long hostId, HttpServletResponse response) {
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
    public ResponseAccDTO getAccommodationById(@PathVariable(name = "accommodationId")Long accommodationId) throws JsonProcessingException {
        return service.findAccommodationById(accommodationId);
    }

    @GetMapping("/accommodation-types")
    public List<String> getAccommodationTypes() {
        return service.findAllAccommodationTypes();
    }

    @GetMapping("/room-types")
    public List<String> getRoomTypes() {
        return service.findAllRoomTypes();
    }

    @GetMapping("/bed-types")
    public List<String> getBedTypes() {
        return service.findAllBedTypes();
    }
}
