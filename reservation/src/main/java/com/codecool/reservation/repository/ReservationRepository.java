package com.codecool.reservation.repository;

import com.codecool.reservation.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository  extends JpaRepository<Reservation, Long> {

    List<Reservation> findReservationsByAccommodationId(Long accommodationId);
    List<Reservation> findReservationsByAccommodationIdAndStartDateGreaterThanEqual(Long accommodationId, LocalDate startDate);
    List<Reservation> findAllReservationsByGuestId(Long guestId);
    List<Reservation> findAllByAccommodationIdAndStartDateBeforeAndEndDateAfter(Long accommodationId, LocalDate startDate, LocalDate endDate);

    List<Reservation> findAllByAccommodationIdAndStartDateBetweenOrEndDateBetween(Long accommodationId, LocalDate startDate, LocalDate endDate, LocalDate startDate1, LocalDate endDate1);

//    @Query(value = "SELECT * from reservation r WHERE r.accommodation_id = :accommodationId", nativeQuery = true)
//    List<Reservation> findResByDatesAndAccId(@Param("accommodationId") Long accommodationId);

    @Query(value = "SELECT * from reservation r WHERE r.accommodation_id = :accommodationId AND(( DATE(r.start_date) between date(:startDate) and date(:endDate)) or (DATE(r.end_date) between date(:startDate) and date(:endDate)))", nativeQuery = true)
    List<Reservation> findResByDatesAndAccId(@Param("accommodationId") Long accommodationId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
