package com.codecool.reservation.controller;

import com.codecool.reservation.model.DTO.DateIntervalDTO;
import com.codecool.reservation.model.DTO.ReservationDTO;
import com.codecool.reservation.model.entity.Reservation;
import com.codecool.reservation.service.ReservationService;
import com.google.common.net.MediaType;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/res")
public class ReservationController {

    private final ReservationService service;

//    @GetMapping("/")
//    public List<Reservation> getAll() {
//        return service.getAllReservation();
//    }

    @GetMapping("/reservation-id/{reservationId}")
    public void getReservationById(@PathVariable Long reservationId) {
        System.out.println("heloooo");
    }

//    @GetMapping("/reservation-id/{reservationId}")
//    public Reservation getReservationById(@PathVariable Long reservationId) {
//        System.out.println("heloooo");
//        return service.getReservationById(reservationId);
//    }

//    @GetMapping("/{reservationId}")
//    public void proba(@PathVariable Long reservationId) {
//        System.out.println("heloooo2");
//    }


//    @GetMapping("/by-accommodation/{accommodationId}")
//    public List<Reservation> getAllReservationByAccommodationId(@PathVariable(name = "accommodationId") Long accommodationId) {
//        return service.getAllReservationByAccommodationId(accommodationId);
//    }

//    @GetMapping("/future-reservations/{accommodationId}")
//    public List<Reservation> getFutureReservations(@PathVariable(name = "accommodationId") Long accommodationId) {
//        return service.getAllFutureReservationByAccommodationId(accommodationId);
//    }
//
    @GetMapping("/by-guest/{guestId}")
    public List<Reservation> getAllReservationByGuestId(@PathVariable(name = "guestId") Long guestId) {
        return service.getAllReservationByGuestId(guestId);
    }
//
//    @PostMapping("/")
//    public void saveNewReservation(@RequestBody ReservationDTO reservationDTO) {
//        service.saveNewReservation(reservationDTO);
//    }
//

//
//    @DeleteMapping(value = "/delete/{reservationId}")
//    public void deleteReservation(@PathVariable Long reservationId) {
//        System.out.println("helloo delete");
//        service.deleteReservation(reservationId);
//    }

//    @RequestMapping(value = "/{reservationId}", method = {RequestMethod.DELETE, RequestMethod.GET})
//    public void deleteReservation(@PathVariable Long reservationId) {
//        System.out.println("helloo delete");
//        service.deleteReservation(reservationId);
//    }

//
    @RequestMapping(value = "/delete/{reservationId}", method = RequestMethod.DELETE)
    public ResponseEntity updateDate(@PathVariable("reservationId") Long reservationId, @RequestBody DateIntervalDTO newDate) {
        service.updateDate(reservationId, newDate);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("/check-res/{accommodationId}")
    public boolean checkIfHasReservationBetweenDates(@PathVariable("accommodationId") Long accommodationId, @RequestBody ReservationDTO reservationDTO){
        boolean valami =service.checkIfHasReservationBetweenDates(accommodationId, reservationDTO.getStartDate(), reservationDTO.getEndDate());
        return valami;
    }

}
