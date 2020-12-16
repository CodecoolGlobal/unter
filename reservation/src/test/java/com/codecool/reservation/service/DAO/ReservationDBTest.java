package com.codecool.reservation.service.DAO;

import com.codecool.reservation.model.entity.Reservation;
import com.codecool.reservation.repository.ReservationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ReservationDBTest {

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private ReservationDB reservationDB;

    @BeforeEach
    private void initializeWithSampleData() {
        repository.deleteAll();

        Reservation reservation1 = Reservation.builder()
                .accommodationId(1L)
                .guestId(1L)
                .startDate(LocalDate.of(2020, 10, 10))
                .endDate(LocalDate.of(2020, 10, 20))
                .build();
        repository.save(reservation1);

        Reservation reservation2 = Reservation.builder()
                .accommodationId(2L)
                .guestId(2L)
                .startDate(LocalDate.of(2020, 11, 10))
                .endDate(LocalDate.of(2020, 11, 20))
                .build();
        repository.save(reservation2);

        Reservation reservation3 = Reservation.builder()
                .accommodationId(1L)
                .guestId(2L)
                .startDate(LocalDate.now().plusDays(7))
                .endDate(LocalDate.now().plusDays(14))
                .build();
        repository.save(reservation3);
    }

    @Test
    public void test_repositoryExists_isNotNull() {
        assertNotNull(repository);
    }

    @Test
    public void test_findAll_isEmpty() {
        repository.deleteAll();
        assertTrue(reservationDB.findAll().isEmpty());
    }

    @Test
    public void test_findAll_hasSizeThree() {
        assertEquals(3, reservationDB.findAll().size());
    }

    @Test
    public void test_findReservationById_ThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> reservationDB.findReservationById(10L));
    }

    @Test
    public void test_findAllByAccommodationId_hasSizeTwo() {
        List<Reservation> reservations = reservationDB.findAllByAccommodationId(1L);
        reservations.forEach(reservation -> assertEquals(1L, reservation.getAccommodationId()));
        assertEquals(2, reservations.size());
    }

    @Test
    public void test_findAllByAccommodationIdFromNow_hasSizeOne() {
        List<Reservation> reservations = reservationDB.findAllByAccommodationIdFromNow(1L);
        reservations.forEach(reservation -> {
            assertEquals(1L, reservation.getAccommodationId());
            assertTrue(LocalDate.now().compareTo(reservation.getStartDate()) < 0);
        });
        assertEquals(1, reservations.size());
    }

    @Test
    public void test_findAllByGuestId_hasSizeTwo() {
        List<Reservation> reservations = reservationDB.findAllByGuestId(2L);
        reservations.forEach(reservation -> assertEquals(2L, reservation.getGuestId()));
        assertEquals(2, reservations.size());
    }

    @Test
    public void test_saveReservation_increasesSizeWithOne() {
        int startSizeOfReservations = reservationDB.findAll().size();
        Reservation reservation = Reservation.builder()
                .accommodationId(3L)
                .guestId(3L)
                .startDate(LocalDate.of(2020, 1, 1))
                .endDate(LocalDate.of(2020, 1,10))
                .build();
        reservationDB.saveReservation(reservation);
        assertEquals(startSizeOfReservations + 1, reservationDB.findAll().size());
    }

    @Test
    public void test_deleteReservation_decreasesSizeWithOne() {
        int startSizeOfReservations = reservationDB.findAll().size();
        Long idOfReservation1 = reservationDB.findAllByGuestId(1L).get(0).getId();
        reservationDB.deleteReservation(idOfReservation1);
        assertEquals(startSizeOfReservations - 1, reservationDB.findAll().size());
    }


}
