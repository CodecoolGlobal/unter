package com.codecool.accommodation.controller;
import com.codecool.accommodation.model.DTO.AccommodationDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationController {

    private AccommodationService service;

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

    @PutMapping("/update-accommodation/{accommodationId}")
    public void updateAccommodation(@PathVariable(name = "accommodationId")String accommodationId, @RequestBody AccommodationDTO accommodationDTO) {
        service.updateAccommodation(accommodationId, accommodationDTO);
    }
}