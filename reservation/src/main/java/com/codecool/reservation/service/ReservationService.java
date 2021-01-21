package com.codecool.reservation.service;

import com.codecool.reservation.model.DTO.DateIntervalDTO;
import com.codecool.reservation.model.DTO.ReservationDTO;
import com.codecool.reservation.model.entity.Reservation;
import com.codecool.reservation.service.DAO.ReservationDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationDAO reservationDAO;

    public List<Reservation> getAllReservation() {
        return reservationDAO.findAll();
    }

    public Reservation getReservationById(Long reservationId) {
        Reservation reservation = null;
        try {
            reservation = reservationDAO.findReservationById(reservationId);
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
        }
        return reservation;
    }

    public List<Reservation> getAllReservationByAccommodationId(Long accommodationId) {
        return reservationDAO.findAllByAccommodationId(accommodationId);
    }

    public List<Reservation> getAllFutureReservationByAccommodationId(Long accommodationId) {
        return reservationDAO.findAllByAccommodationIdFromNow(accommodationId);
    }

    public List<Reservation> getAllReservationByGuestId(Long guestId) {
        List<Reservation> allByGuestId = reservationDAO.findAllByGuestId(guestId);
        for(Reservation reservation: allByGuestId){
            reservation.
        }
        return reservationDAO.findAllByGuestId(guestId);
    }

    public void saveNewReservation(ReservationDTO newReservation) {
        if (newReservation.getAccommodationId() != null ||
                newReservation.getGuestId() != null ||
                newReservation.getStartDate() != null ||
                newReservation.getEndDate() != null) {

            Reservation reservation = Reservation.builder()
                    .accommodationId(newReservation.getAccommodationId())
                    .guestId(newReservation.getGuestId())
                    .startDate(newReservation.getStartDate())
                    .endDate(newReservation.getEndDate())
                    .build();
            reservationDAO.saveReservation(reservation);
        } else {
            log.warn("Required fields of reservationDTO are null.");
        }

    }

    public void deleteReservation(Long reservationId) {
        try {
            reservationDAO.deleteReservation(reservationId);
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
        }
    }

    public void updateDate(Long reservationId, DateIntervalDTO newDate) {
        if (newDate.getStartDate() != null ||
                newDate.getEndDate() != null) {

            Reservation reservation = reservationDAO.findReservationById(reservationId);
            reservation.setStartDate(newDate.getStartDate());
            reservation.setEndDate(newDate.getEndDate());

            reservationDAO.saveReservation(reservation);
        } else {
            log.warn("Required fields of newDate are null.");
        }
    }

    public boolean checkIfHasReservationBetweenDates(Long accommodationId, LocalDate startDate, LocalDate endDate){
        List<Reservation> reservations1 = reservationDAO.checkIfHasReservationBeforeAndAfterDates(accommodationId, startDate, endDate);
        if(reservations1.isEmpty()){
            return false;
        } else {
            return true;
        }
    }
}
