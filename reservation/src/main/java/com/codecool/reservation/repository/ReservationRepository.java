package com.codecool.reservation.repository;

import com.codecool.reservation.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository  extends JpaRepository<Reservation, Long> {

    List<Reservation> findAll();
    List<Reservation> findReservationsByAccommodationId(Long accommodationId);
    List<Reservation> findReservationsByAccommodationIdAndStartDateGreaterThanEqual(Long accommodationId, LocalDate startDate);
    List<Reservation> findAllReservationsByGuestId(Long guestId);
    void deleteById(Long reservationId);
}
