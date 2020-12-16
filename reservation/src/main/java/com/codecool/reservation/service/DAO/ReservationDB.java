package com.codecool.reservation.service.DAO;

import com.codecool.reservation.model.DTO.ReservationDTO;
import com.codecool.reservation.model.entity.Reservation;
import com.codecool.reservation.repository.ReservationRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@Data
@RequiredArgsConstructor
public class ReservationDB implements ReservationDAO{

    private final ReservationRepository repository;

    @Override
    public List<Reservation> findAll() {
        return repository.findAll();
    }

    @Override
    public Reservation findReservationById(Long reservationId) throws NoSuchElementException {
        return repository.findById(reservationId)
                .orElseThrow(() -> new NoSuchElementException("No reservation was found with this id"));
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
    public void saveNewReservation(ReservationDTO reservationDTO) {
        Reservation reservation = Reservation.builder()
                .accommodationId(reservationDTO.getAccommodationId())
                .guestId(reservationDTO.getGuestId())
                .startDate(reservationDTO.getStartDate())
                .endDate(reservationDTO.getEndDate())
                .build();
        try {
            repository.save(reservation);
        } catch (DataIntegrityViolationException exception) {
                exception.printStackTrace();
        }
    }

    @Override
    public void deleteReservation(Long reservationId) {
        try {
            repository.deleteById(reservationId);
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void updateDate(Long reservationId, LocalDate startDate, LocalDate endDate) {
        Reservation reservation = findReservationById(reservationId);

        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);

        try {
            repository.save(reservation);
        } catch (DataIntegrityViolationException exception) {
            exception.printStackTrace();
        }
    }

}
