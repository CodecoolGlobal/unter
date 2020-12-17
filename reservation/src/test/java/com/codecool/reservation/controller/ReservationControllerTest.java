package com.codecool.reservation.controller;

import com.codecool.reservation.model.DTO.DateIntervalDTO;
import com.codecool.reservation.model.DTO.ReservationDTO;
import com.codecool.reservation.model.entity.Reservation;
import com.codecool.reservation.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class ReservationControllerTest {

    @Autowired
    private ReservationController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService service;

    private Reservation testReservation;

    private ReservationDTO testReservationDTO;

    private DateIntervalDTO newDate;

    @BeforeEach
    public void setUp() {
        testReservation = Reservation.builder()
                .id(1L)
                .accommodationId(2L)
                .guestId(3L)
                .startDate(LocalDate.of(2020, 12, 10))
                .endDate(LocalDate.of(2020, 12, 20))
                .build();
        testReservationDTO = ReservationDTO.builder()
                .accommodationId(2L)
                .guestId(3L)
                .startDate(LocalDate.of(2020, 12, 10))
                .endDate(LocalDate.of(2020, 12, 20))
                .build();
        newDate = new DateIntervalDTO(LocalDate.of(2020, 12, 20), LocalDate.of(2020, 12, 30));
    }

    @Test
    public void contextLoads() {
        assertNotNull(controller);
    }

    @Test
    public void test_getAll_returnsList() throws Exception {
        Long id = testReservation.getId();
        when(service.getAllReservation()).thenReturn(List.of(testReservation));

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(id))
                .andDo(print());
    }

    @Test
    public void test_getReservationById_isFound() throws Exception {
        Long id = testReservation.getId();
        when(service.getReservationById(id)).thenReturn(testReservation);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/reservation/{reservationId}", id))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andDo(print());
    }

    @Test
    public void test_getAllReservationByAccommodationId_returnsList() throws Exception {
        Long accommodationId = testReservation.getAccommodationId();
        when(service.getAllReservationByAccommodationId(accommodationId)).thenReturn(List.of(testReservation));

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/by-accommodation/{accommodationId}", accommodationId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].accommodationId").value(accommodationId))
                .andDo(print());
    }

    @Test
    public void test_getFutureReservations_returnsList() throws Exception {
        Long accommodationId = testReservation.getAccommodationId();
        when(service.getAllFutureReservationByAccommodationId(accommodationId)).thenReturn(List.of(testReservation));

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/future-reservations/{accommodationId}", accommodationId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].accommodationId").value(accommodationId))
                .andDo(print());
    }

    @Test
    public void test_getAllReservationByGuestId_returnsList() throws Exception {
        Long guestId = testReservation.getGuestId();
        when(service.getAllReservationByGuestId(guestId)).thenReturn(List.of(testReservation));

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/by-guest/{guestId}", guestId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].guestId").value(guestId))
                .andDo(print());
    }

    @Test
    public void test_saveNewReservation_statusIsOk() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testReservationDTO)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(service, times(1)).saveNewReservation(any(ReservationDTO.class));
    }

    @Test
    public void test_deleteReservation_StatusIsOk() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/{reservationId}", 1L))
                .andExpect(status().isOk())
                .andDo(print());

        verify(service, times(1)).deleteReservation(1L);
    }

    @Test
    public void test_updateDate_statusIsOk() throws Exception {
        Long id = testReservation.getId();
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/{reservationId}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newDate)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(service, times(1)).updateDate(id, newDate);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
