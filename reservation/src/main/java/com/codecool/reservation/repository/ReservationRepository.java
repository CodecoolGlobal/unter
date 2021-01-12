package com.codecool.reservation.repository;

import com.codecool.reservation.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository  extends JpaRepository<Reservation, Long> {

    List<Reservation> findReservationsByAccommodationId(Long accommodationId);
    List<Reservation> findReservationsByAccommodationIdAndStartDateGreaterThanEqual(Long accommodationId, LocalDate startDate);
    List<Reservation> findAllReservationsByGuestId(Long guestId);
    List<Reservation> findAllByAccommodationIdAndStartDateAfterAndEndDateBefore(Long accommodationId, LocalDate startDate, LocalDate endDate);
}
