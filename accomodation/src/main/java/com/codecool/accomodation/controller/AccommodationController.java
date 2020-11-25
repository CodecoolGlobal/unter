package com.codecool.accomodation.controller;
import com.codecool.accomodation.model.DTO.AccommodationDTO;
import com.codecool.accomodation.model.entity.Accommodation;
import com.codecool.accomodation.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService service;

    @GetMapping("/get-all/{hostId}")
    public List<Accommodation> getAllAccommodation(@PathVariable(name = "hostId") String hostId) {
        return  service.getAllAccommodation(hostId);
    }

    @PostMapping("/new-accommodation")
    public void saveNewAccommodation(@RequestBody AccommodationDTO accommodationDTO) {
        service.saveNewAccommodation(accommodationDTO);
    }

    @DeleteMapping("/delete-accommodation/{accommodationId}")
    public void deleteAccommodation(@PathVariable(name = "accommodationId") String accommodationId) {
        service.deleteAccommodation(accommodationId);
    }

    @PutMapping("/update-accommondaiton/{accommodationId}")
    public void updateAccommodation(@PathVariable(name = "accommodationId")String accommodationId, @RequestBody AccommodationDTO accommodationDTO) {
        service.updateAccommodation(accommodationId, accommodationDTO);
    }
}
