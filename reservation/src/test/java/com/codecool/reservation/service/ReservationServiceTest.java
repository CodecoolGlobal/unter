package com.codecool.reservation.service;

import com.codecool.reservation.model.DTO.DateIntervalDTO;
import com.codecool.reservation.model.DTO.ReservationDTO;
import com.codecool.reservation.model.entity.Reservation;
import com.codecool.reservation.service.DAO.ReservationDAO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
@ActiveProfiles("test")
public class ReservationServiceTest {

    @Mock
    private ReservationDAO reservationDAO;

    @InjectMocks
    private ReservationService service;

    private Reservation testReservation = Reservation.builder()
            .accommodationId(1L)
            .guestId(1L)
            .startDate(LocalDate.of(2020, 12, 10))
            .endDate(LocalDate.of(2020, 12, 20))
            .build();

    @Test
    public void test_getAllReservation_returnsListFromDao() {
        when(reservationDAO.findAll()).thenReturn(List.of(testReservation));
        List<Reservation> returnedReservations = service.getAllReservation();
        assertTrue(returnedReservations.contains(testReservation));
        assertEquals(1, returnedReservations.size());
    }

    @Test
    public void test_getAllReservation_returnsEmptyList() {
        when(reservationDAO.findAll()).thenReturn(List.of());
        assertTrue(service.getAllReservation().isEmpty());
    }


    @Test
    public void test_getReservationById_returnsReservationFromDao() {
        Long id = testReservation.getId();
        when(reservationDAO.findReservationById(id)).thenReturn(testReservation);
        assertEquals(id, service.getReservationById(id).getId());
    }

    @Test
    public void test_getReservationById_returnsNull() {
        Long id = 100L;
        when(reservationDAO.findReservationById(id)).thenThrow(new NoSuchElementException());
        assertNull(service.getReservationById(id));
    }

    @Test
    public void test_getAllReservationByAccommodationId_returnsListFromDao() {
        Long accommodationId = testReservation.getAccommodationId();
        when(reservationDAO.findAllByAccommodationId(accommodationId)).thenReturn(List.of(testReservation));
        List<Reservation> returnedReservations = service.getAllReservationByAccommodationId(accommodationId);
        assertTrue(returnedReservations.contains(testReservation));
        assertEquals(1, returnedReservations.size());
    }

    @Test
    public void test_getAllReservationByAccommodationId_returnsEmptyList() {
        Long accommodationId = 100L;
        when(reservationDAO.findAllByAccommodationId(accommodationId)).thenReturn(List.of());
        assertTrue(service.getAllReservationByAccommodationId(accommodationId).isEmpty());
    }

    @Test
    public void test_getAllFutureReservationByAccommodationId_returnsListFromDao() {
        Long accommodationId = 2L;
        Reservation futureReservation = Reservation.builder()
                .accommodationId(accommodationId)
                .guestId(2L)
                .startDate(LocalDate.now().plusDays(1))
                .endDate(LocalDate.now().plusDays(10))
                .build();
        when(reservationDAO.findAllByAccommodationIdFromNow(accommodationId)).thenReturn(List.of(futureReservation));
        List<Reservation> returnedReservations = service.getAllFutureReservationByAccommodationId(accommodationId);
        assertTrue(returnedReservations.contains(futureReservation));
        assertEquals(1, returnedReservations.size());
    }

    @Test
    public void test_getAllFutureReservationByAccommodationId_returnsEmptyList() {
        Long accommodationId = testReservation.getAccommodationId();
        when(reservationDAO.findAllByAccommodationIdFromNow(accommodationId)).thenReturn(List.of());
        assertTrue(service.getAllFutureReservationByAccommodationId(accommodationId).isEmpty());
    }

    @Test
    public void test_getAllReservationByGuestId_returnsListFromDao() {
        Long guestId = testReservation.getGuestId();
        when(reservationDAO.findAllByGuestId(guestId)).thenReturn(List.of(testReservation));
        List<Reservation> returnedReservations = service.getAllReservationByGuestId(guestId);
        assertTrue(returnedReservations.contains(testReservation));
        assertEquals(1, returnedReservations.size());
    }

    @Test
    public void test_getAllReservationByGuestId_returnsEmptyList() {
        Long guestId = 100L;
        when(reservationDAO.findAllByGuestId(guestId)).thenReturn(List.of());
        assertTrue(service.getAllReservationByGuestId(guestId).isEmpty());
    }

    @Test
    public void test_saveNewReservation_saveReservationIsCalled() {
        ReservationDTO reservationDTO = ReservationDTO.builder()
                .accommodationId(1L)
                .guestId(1L)
                .startDate(LocalDate.of(2020, 12, 10))
                .endDate(LocalDate.of(2020, 12, 20))
                .build();
        service.saveNewReservation(reservationDTO);
        verify(reservationDAO).saveReservation(testReservation);
    }

    @Test
    public void test_saveNewReservation_saveReservationIsNotCalled() {
        ReservationDTO reservationAccIdNull = ReservationDTO.builder()
                .accommodationId(null)
                .guestId(1L)
                .startDate(LocalDate.of(2020, 12, 10))
                .endDate(LocalDate.of(2020, 12, 20))
                .build();
        service.saveNewReservation(reservationAccIdNull);

        ReservationDTO reservationGuestIdNull = ReservationDTO.builder()
                .accommodationId(1L)
                .guestId(null)
                .startDate(LocalDate.of(2020, 12, 10))
                .endDate(LocalDate.of(2020, 12, 20))
                .build();
        service.saveNewReservation(reservationGuestIdNull);

        ReservationDTO reservationStartDateNull = ReservationDTO.builder()
                .accommodationId(1L)
                .guestId(1L)
                .startDate(null)
                .endDate(LocalDate.of(2020, 12, 20))
                .build();
        service.saveNewReservation(reservationStartDateNull);

        ReservationDTO reservationEndDateNull = ReservationDTO.builder()
                .accommodationId(1L)
                .guestId(1L)
                .startDate(LocalDate.of(2020, 12, 10))
                .endDate(null)
                .build();
        service.saveNewReservation(reservationEndDateNull);

        verify(reservationDAO, times(0)).saveReservation(testReservation);
    }

    @Test
    public void test_deleteReservation_deleteReservationIsCalled() {
        Long id = 100L;
        service.deleteReservation(id);
        verify(reservationDAO).deleteReservation(id);
    }

    @Test
    public void test_updateDate_saveReservationIsCalled() {
        Long idOfTestReservation = testReservation.getId();
        DateIntervalDTO newDate = new DateIntervalDTO(LocalDate.of(2020, 10, 20), LocalDate.of(2020, 10, 30));

        when(reservationDAO.findReservationById(idOfTestReservation)).thenReturn(testReservation);
        service.updateDate(idOfTestReservation, newDate);

        verify(reservationDAO).saveReservation(testReservation);
    }

    @Test
    public void test_updateDate_saveReservationIsNotCalled() {
        Long idOfTestReservation = testReservation.getId();
        DateIntervalDTO newDate = new DateIntervalDTO(null, null);

        when(reservationDAO.findReservationById(idOfTestReservation)).thenReturn(testReservation);
        service.updateDate(idOfTestReservation, newDate);

        verify(reservationDAO, times(0)).saveReservation(testReservation);
    }


}
