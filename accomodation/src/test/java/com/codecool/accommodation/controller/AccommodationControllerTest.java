package com.codecool.accommodation.controller;

import com.codecool.accommodation.model.DTO.NewAccommodationDTO;
import com.codecool.accommodation.model.entity.*;
import com.codecool.accommodation.model.entity.types.AccommodationType;
import com.codecool.accommodation.model.entity.types.BedType;
import com.codecool.accommodation.model.entity.types.RoomType;
import com.codecool.accommodation.service.AccommodationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

//@AutoConfigureMockMvc
@WebMvcTest(AccommodationController.class)
@ActiveProfiles("test")
public class AccommodationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // mock because we only need the functionalities
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

        Set<Room> rooms = new HashSet<>();
        rooms.add(room);

        testAccommodation = Accommodation.builder()
            .id(1L)
            .hostId(1L)
            .rooms(rooms)
            .description("Test")
            .coordinate(coordinate)
            .address(address)
            .maxNumberOfGuests(4)
            .name("Test")
            .build();

        testNewAccommodationDTO = NewAccommodationDTO.builder()
            .hostId(1L)
            .name("Test")
            .description("Test")
            .coordinate(coordinate)
            .type(AccommodationType.APARTMENT)
            .maxNumberOfGuest(3)
            .rooms(rooms)
            .address(address)
            .build();
    }

    @Test
    public void smokeTest() {
        assertThat(service).isNotNull();
    }

    @Test
    public void test_nonExistingEndpoint_ShouldNotWork() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/dummy")
                .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void test_findAllAccommodationsEndpoint_ShouldRunAndGetArray() throws Exception {
        List<Accommodation> accommodations = new ArrayList<>();
        accommodations.add(testAccommodation);

        when(service.findAll()).thenReturn(accommodations);

        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].hostId").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("[0].maxNumberOfGuests").value(4))
            .andExpect(MockMvcResultMatchers.jsonPath("[0].description").value("Test"));

        verify(service, times(1)).findAll();
    }

    @Test
    public void test_findAccommodationById_ShouldBeFound() throws Exception {
        when(service.findAccommodationById(1L)).thenReturn(testNewAccommodationDTO);

        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/accommodation-id/{accommodationId}", 1L)
                .characterEncoding("utf-8"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("Test"))
            .andExpect(jsonPath("$.description").value("Test"))
            .andExpect(status().is2xxSuccessful())
            .andDo(print());

        verify(service, times(1)).findAccommodationById(1L);
    }

    @Test //TODO
    public void test_findAccommodationsByNonExistingId_ShouldNotBeFound() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/host-id/{hostId}", 5000L)
                .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void test_findAccommodationsByHostId_shouldReturnArray() throws Exception {
        List<Accommodation> accommodations = new ArrayList<>();
        accommodations.add(testAccommodation);

        when(service.getAllAccommodation(1L)).thenReturn(accommodations);

        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/host-id/{hostId}", 1L)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].hostId").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("[0].maxNumberOfGuests").value(4))
            .andExpect(MockMvcResultMatchers.jsonPath("[0].description").value("Test"));

        verify(service, times(1)).getAllAccommodation(1L);
    }

    @Test //todo
    public void test_findAccommodationByNonExistingHostId_shouldNotBeFound() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/accommodation-id/{accommodationId}", 5000L)
                .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void test_saveNewAccommodationEndpoint_StatusIsOk() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders
                .post("/")
                .characterEncoding("utf-8")
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
                .put("/accommodation-id/{accommodationId}", testId)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testAccommodation)))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    public void test_deleteAccommodationByIdEndpoint_StatusIsOk() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders
                .delete("/accommodation-id/{accommodationId}", 1L)
                .characterEncoding("utf-8"))
            .andExpect(status().isOk())
            .andDo(print());

        verify(service, times(1)).deleteAccommodation(1L);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}