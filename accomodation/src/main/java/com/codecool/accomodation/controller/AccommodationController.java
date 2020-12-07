package com.codecool.accomodation.controller;

import com.codecool.accomodation.model.DTO.AccommodationDTO;
import com.codecool.accomodation.model.entity.Accommodation;
import com.codecool.accomodation.service.AccommodationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/a")
public class AccommodationController {

    private final AccommodationService service;

    @GetMapping("/get-all/{hostId}")
    public List<Accommodation> getAllAccommodation(@PathVariable(name = "hostId") String hostId, HttpServletResponse response) {
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

    @PostMapping("/new-accommodation")
    public void saveNewAccommodation(@RequestBody AccommodationDTO accommodationDTO) {
        service.saveNewAccommodation(accommodationDTO);
    }

    @DeleteMapping("/delete-accommodation/{accommodationId}")
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

    @PutMapping("/update-accommodation/{accommodationId}")
    public void updateAccommodation(@PathVariable(name = "accommodationId") String accommodationId, @RequestBody AccommodationDTO accommodationDTO) {
        service.updateAccommodation(accommodationId, accommodationDTO);
    }
}
