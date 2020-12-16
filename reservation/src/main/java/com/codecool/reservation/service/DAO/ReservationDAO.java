package com.codecool.reservation.service.DAO;

import com.codecool.reservation.model.entity.Reservation;

import java.util.List;
import java.util.NoSuchElementException;

public interface ReservationDAO {

    List<Reservation> findAll();
    Reservation findReservationById(Long reservationId) throws NoSuchElementException;
    List<Reservation> findAllByAccommodationId(Long accommodationId);
    List<Reservation> findAllByAccommodationIdFromNow(Long accommodationId);
    List<Reservation> findAllByGuestId(Long guestId);
    void saveReservation(Reservation reservation);
    void deleteReservation(Long reservationId) throws NoSuchElementException;
}
