package com.codecool.repository;

import com.codecool.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository  extends JpaRepository<Reservation, Long> {

    List<Reservation> findAll();
    List<Reservation> findReservationsByAccommodationId(Long reservationId);
    void deleteReservationById(Long reservationId);
}
