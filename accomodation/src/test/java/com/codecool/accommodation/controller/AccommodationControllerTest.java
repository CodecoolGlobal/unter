package com.codecool.accommodation.controller;

import com.codecool.accommodation.model.DTO.AccommodationDTO;
import com.codecool.accommodation.model.entity.*;
import com.codecool.accommodation.service.AccommodationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class AccommodationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccommodationService service;

    private Accommodation testAccommodation;

    private AccommodationDTO testAccommodationDTO;

    @BeforeEach
    public void setUp() {
        Address testAddress = Address.builder()
            .houseNumber(2)
            .street("Test street")
            .city("Test City")
            .zipCode("test")
            .build();

        Location testLocation = Location.builder()
            .coordinate(Coordinate.builder()
                .latitude(45.45)
                .longitude(23.23)
                .build())
            .address(testAddress)
            .build();

        testAccommodation = Accommodation.builder()
            .id(1L)
            .host(Host.builder()
                .id(1L)
                .email("test@test.com")
                .phone("test")
                .build())
            .name("Test")
            .maxNumberOfGuests(10)
            .location(testLocation)
            .build();

        testAccommodationDTO = AccommodationDTO.builder()
            .host(Host.builder()
                .id(1L)
                .email("test@test.com")
                .phone("test")
                .build())
            .name("Test")
            .maxNumberOfGuest(10)
            .location(testLocation)
            .build();
    }

    @Test // do we need this, if it's mocked?
    public void smokeTest() {
        assertThat(service).isNotNull();
    }

    @Test
    public void test_getAllAccommodationEndpoint_ShouldRunAndGetArrayFromService() throws Exception {
        List<Accommodation> accommodations = new ArrayList<>();
        accommodations.add(testAccommodation);

        String hostId = "1";
        when(service.getAllAccommodation(hostId)).thenReturn(accommodations);

        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/get-all/{hostId}", 1L)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].host.email").value("test@test.com"));

        verify(service, times(1)).getAllAccommodation(hostId);
    }

    @Test
    public void test_nonExistingEndpoint_ShouldNotWork() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get("/dummy"))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    // TODO
    @Test
    public void test_findAccommodationsByNonExistingId_ShouldNotBeFound() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/get-all/{hostId}", 6L))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void test_saveNewAccommodationEndpoint_ShouldRunAndSave() throws Exception {
        Address address = Address.builder()
            .houseNumber(15)
            .street("Test Street")
            .city("Test City")
            .zipCode("test zip code")
            .build();

        Location location = Location.builder()
            .coordinate(Coordinate.builder()
                .latitude(35.45)
                .longitude(23.23)
                .build())
            .address(address)
            .build();

        AccommodationDTO accommodation = AccommodationDTO.builder()
            .host(Host.builder()
                .id(1L)
                .email("testytest@test.com")
                .phone("666")
                .build())
            .maxNumberOfGuest(5)
            .name("Test Accommodation5")
            .location(location)
            .build();

        mockMvc
            .perform(MockMvcRequestBuilders
                .post("/new-accommodation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(accommodation)))
            .andDo(print())
            .andExpect(status().is2xxSuccessful());

        verify(service, times(1)).saveNewAccommodation(any(AccommodationDTO.class));
    }

    @Test
    public void test_updateAccommodation_ShouldSaveUpdates() {
        Address address = Address.builder()
            .houseNumber(15)
            .street("Test Street")
            .city("Test City")
            .zipCode("test zip code")
            .build();

        Location location = Location.builder()
            .coordinate(Coordinate.builder()
                .latitude(35.45)
                .longitude(23.23)
                .build())
            .address(address)
            .build();

        Accommodation accommodation = Accommodation.builder()
            .host(Host.builder()
                .id(1L)
                .email("testytest@test.com")
                .phone("666")
                .build())
            .maxNumberOfGuests(5)
            .name("Test Accommodation5")
            .location(location)
            .build();

        Accommodation accommodation2 = Accommodation.builder()
            .host(Host.builder()
                .id(1L)
                .email("testytest@test.com")
                .phone("666")
                .build())
            .maxNumberOfGuests(5)
            .name("Test Accommodation5")
            .location(location)
            .build();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}