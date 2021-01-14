package com.codecool.reservation.service.DAO;

import com.codecool.reservation.model.entity.Reservation;
import com.codecool.reservation.repository.ReservationRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@Data
@RequiredArgsConstructor
@Slf4j
public class ReservationDB implements ReservationDAO{

    private final ReservationRepository repository;

    @Override
    public List<Reservation> findAll() {
        return repository.findAll();
    }

    @Override
    public Reservation findReservationById(Long reservationId) throws NoSuchElementException {
        return repository.findById(reservationId)
                .orElseThrow(() -> new NoSuchElementException("No reservation was found with this id: " + reservationId));
    }

    @Override
    public List<Reservation> findAllByAccommodationId(Long accommodationId) {
        return repository.findReservationsByAccommodationId(accommodationId);
    }

    @Override
    public List<Reservation> findAllByAccommodationIdFromNow(Long accommodationId) {
        return repository.findReservationsByAccommodationIdAndStartDateGreaterThanEqual(accommodationId, LocalDate.now());
    }

    @Override
    public List<Reservation> findAllByGuestId(Long guestId) {
        return repository.findAllReservationsByGuestId(guestId);
    }

    @Override
    public void saveReservation(Reservation reservation) {
        try {
            repository.save(reservation);
        } catch (DataIntegrityViolationException exception) {
                log.error(Arrays.toString(exception.getStackTrace()));
        }
    }

    @Override
    public void deleteReservation(Long reservationId) {
        try {
            repository.deleteById(reservationId);
        } catch (EmptyResultDataAccessException exception) {
            log.warn(exception.getMessage());
        }
    }


    @Override
    public List<Reservation> checkIfHasReservationBeforeAndAfterDates(Long accommodationId, LocalDate startDate, LocalDate endDate) {
        return repository.findResByDatesAndAccId(accommodationId, startDate, endDate);
    }

}
