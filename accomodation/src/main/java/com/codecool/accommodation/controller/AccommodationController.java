package com.codecool.accommodation.controller;

import com.codecool.accommodation.model.DTO.AccommodationDTO;
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

    @GetMapping("")
    public List<Accommodation> getAll(){
        return service.findAll();
    }

    @GetMapping("/{hostId}")
    public List<Accommodation> getAllAccommodationByHost(@PathVariable(name = "hostId") String hostId, HttpServletResponse response) {
        List<Accommodation> accommodations = service.getAllAccommodation(hostId);

        if (accommodations == null) {
            response.setStatus(401);
            try {
                response.getWriter().println("Host id cannot be null!!!");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return accommodations;
    }

    @PostMapping("/new")
    public void saveNewAccommodation(@RequestBody AccommodationDTO accommodationDTO) {
        service.saveNewAccommodation(accommodationDTO);
    }

    @DeleteMapping("/{accommodationId}")
    public void deleteAccommodation(@PathVariable(name = "accommodationId") String accommodationId, HttpServletResponse response) {
        if(accommodationId == null){
            response.setStatus(401);
            try {
                response.getWriter().println("Id cannot be null!!");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        service.deleteAccommodation(accommodationId);
    }

    @PutMapping("/{accommodationId}")
    public void updateAccommodation(@PathVariable(name = "accommodationId") String accommodationId, @RequestBody AccommodationDTO accommodationDTO) {
        service.updateAccommodation(accommodationId, accommodationDTO);
    }

    @GetMapping("/get-accommodation/{accommodationId}")
    public Accommodation getAccommodationById(@PathVariable(name = "accommodationId")Long accommodationId) {
        return service.findAccommodationById(accommodationId);
    }
}
