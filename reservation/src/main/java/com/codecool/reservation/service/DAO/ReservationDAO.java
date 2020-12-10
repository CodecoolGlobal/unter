package com.codecool.reservation.service.DAO;

import com.codecool.reservation.model.DTO.ReservationDTO;
import com.codecool.reservation.model.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDAO {

    List<Reservation> findAll();
    List<Reservation> findAllByAccommodationId(Long accommodationId);
    List<Reservation> findAllByAccommodationIdFromNow(Long accommodationId);
    List<Reservation> findAllByGuestId(Long guestId);
    void saveNewReservation(ReservationDTO reservationDTO);
    void deleteReservation(Long reservationId);
    Reservation findReservationById(Long reservationId);
    void updateReservation(Long reservationId, LocalDate startDate, LocalDate endDate);
}
