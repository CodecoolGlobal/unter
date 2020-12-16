package com.codecool.accommodation.controller;

import com.codecool.accommodation.model.DTO.NewAccommodationDTO;
import com.codecool.accommodation.model.entity.*;
import com.codecool.accommodation.model.entity.types.BedType;
import com.codecool.accommodation.model.entity.types.RoomType;
import com.codecool.accommodation.service.AccommodationService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.*;

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

    private NewAccommodationDTO testNewAccommodationDTO;

    @BeforeEach
    public void setUp() {
        Address address = Address.builder()
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber(12)
            .build();

        Coordinate coordinate = Coordinate.builder()
            .latitude(22.00)
            .longitude(32.00)
            .build();

        Map<BedType, Integer> beds = new HashMap<>();
        beds.put(BedType.KING, 1);

        Room room = Room.builder()
            .type(RoomType.BEDROOM)
            .beds(beds)
            .build();

        testAccommodation = Accommodation.builder()
            .hostId(1L)
            .rooms(null) //TODO
            .description("Test")
            .coordinate(coordinate)
            .address(address)
            .maxNumberOfGuests(4000)
            .name("Test")
            .build();

        testNewAccommodationDTO = NewAccommodationDTO.builder()
            .hostId(1L)
            .description("Test")
            .coordinate(coordinate)
            .address(address)
            .maxNumberOfGuest(4000)
            .name("Test")
            .build();
    }

    @Test // do we need this, if it's mocked?
    public void smokeTest() {
        assertThat(service).isNotNull();
    }

    @Test
    public void test_getAllAccommodationEndpoint_ShouldRunAndGetArray() throws Exception {
        List<Accommodation> accommodations = new ArrayList<>();
        accommodations.add(testAccommodation);

        Long hostId = 1L;
        when(service.getAllAccommodation(hostId)).thenReturn(accommodations);

        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/{hostId}", hostId)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("[0].description").value("Test"));

        verify(service, times(1)).getAllAccommodation(hostId);
    }

    @Test
    public void test_nonExistingEndpoint_ShouldNotWork() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get("/dummy"))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void test_findAccommodationsByNonExistingId_ShouldNotBeFound() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/{hostId}", 6L))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void test_saveNewAccommodationEndpoint_StatusIsOk() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders
                .post("")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testNewAccommodationDTO)))
            .andExpect(status().isOk())
            .andDo(print());

        verify(service, times(1)).saveNewAccommodation(any(NewAccommodationDTO.class));
    }

    @Test
    public void test_updateAccommodationEndpoint_StatusIsOk() throws Exception {
        Long testId = 1L;
        mockMvc
            .perform(MockMvcRequestBuilders
                .put("/{accommodationId}", testId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testAccommodation)))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    public void test_deleteAccommodationByIdEndpoint_StatusIsOk() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders
                .delete("{accommodationId}", 1L))
            .andExpect(status().isOk())
            .andDo(print());

        verify(service, times(1)).deleteAccommodation(1L);
    }

    @Test
    public void test_findAccommodationById_ShouldBeFound() throws Exception {
        Long testId = 1L;
        when(service.findAccommodationById(testId)).thenReturn(testAccommodation);

        mockMvc
            .perform(MockMvcRequestBuilders
                .get("{accommodationId}", testId))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(testId))
            .andExpect(jsonPath("$.name").value("Test"))
            .andDo(print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}