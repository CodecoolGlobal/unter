package com.codecool.reservation.controller;

import com.codecool.reservation.model.DTO.DateIntervalDTO;
import com.codecool.reservation.model.DTO.ReservationDTO;
import com.codecool.reservation.model.entity.Reservation;
import com.codecool.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @GetMapping("")
    public List<Reservation> getAll() {
        return service.getAllReservation();
    }

    @GetMapping("/reservation/{reservationId}")
    public Reservation getReservationById(@PathVariable(name = "reservationId") Long reservationId) {
        return service.getReservationById(reservationId);
    }

    @GetMapping("/by-accommodation/{accommodationId}")
    public List<Reservation> getAllReservationByAccommodationId(@PathVariable(name = "accommodationId") Long accommodationId) {
        return service.getAllReservationByAccommodationId(accommodationId);
    }

    @GetMapping("/future-reservations/{accommodationId}")
    public List<Reservation> getFutureReservations(@PathVariable(name = "accommodationId") Long accommodationId) {
        return service.getAllFutureReservationByAccommodationId(accommodationId);
    }

    @GetMapping("/by-guest/{guestId}")
    public List<Reservation> getAllReservationByGuestId(@PathVariable(name = "guestId") Long guestId) {
        return service.getAllReservationByGuestId(guestId);
    }

    @PostMapping
    public void saveNewReservation(@RequestBody ReservationDTO reservationDTO) {
        service.saveNewReservation(reservationDTO);
    }

    @DeleteMapping("/{reservationId}")
    public void deleteReservation(@PathVariable(name = "reservationId") Long reservationId) {
        service.deleteReservation(reservationId);
    }

    @PutMapping("/{reservationId}")
    public void updateDate(@PathVariable(name = "reservationId") Long reservationId, @RequestBody DateIntervalDTO newDate) {
        service.updateDate(reservationId, newDate);
    }

}
