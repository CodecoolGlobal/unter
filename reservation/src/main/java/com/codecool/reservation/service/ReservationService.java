package com.codecool.reservation.service;

import com.codecool.reservation.model.DTO.DateIntervalDTO;
import com.codecool.reservation.model.DTO.ReservationDTO;
import com.codecool.reservation.model.entity.Reservation;
import com.codecool.reservation.service.DAO.ReservationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationDAO reservationDAO;

    public List<Reservation> getAll() {
        return reservationDAO.findAll();
    }

    public Reservation getReservationById(Long reservationId) {
        Reservation reservation = null;
        try {
            reservation = reservationDAO.findReservationById(reservationId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
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
        return reservationDAO.findAllByGuestId(guestId);
    }

    public void saveNewReservation(ReservationDTO reservationDTO) {
        try {
            if (reservationDTO.getAccommodationId() != null ||
                    reservationDTO.getGuestId() != null ||
                    reservationDTO.getStartDate() != null ||
                    reservationDTO.getEndDate() != null) {

                reservationDAO.saveNewReservation(reservationDTO);
            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
    }

    public void deleteReservation(Long reservationId) {
        reservationDAO.deleteReservation(reservationId);
    }

    public void updateDate(Long reservationId, DateIntervalDTO newDate) {
        try {
            if (newDate.getStartDate() != null ||
                    newDate.getEndDate() != null) {
                reservationDAO.updateDate(reservationId, newDate.getStartDate(), newDate.getEndDate());
            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
    }
}
