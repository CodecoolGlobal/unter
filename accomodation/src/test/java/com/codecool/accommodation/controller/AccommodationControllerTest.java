package com.codecool.accommodation.controller;

import com.codecool.accommodation.model.entity.*;
import com.codecool.accommodation.service.AccommodationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class AccommodationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccommodationService service;

    private List<Accommodation> accommodations;

    @BeforeEach
    void setUp() {
        accommodations = new ArrayList<>();

        Address address1 = Address.builder()
            .houseNumber(2)
            .street("Érc utca")
            .city("Budapest")
            .zipCode("1032")
            .build();

        Address address2 = Address.builder()
            .houseNumber(15)
            .street("Viador utca")
            .city("Budapest")
            .zipCode("1034")
            .build();

        Address address3 = Address.builder()
            .houseNumber(22)
            .street("Hegedűs Gyula utca")
            .city("Budapest")
            .zipCode("1136")
            .build();

        Location location1 = Location.builder()
            .coordinate(Coordinate.builder()
                .latitude(45.45)
                .longitude(23.23)
                .build())
            .address(address1)
            .build();

        Location location2 = Location.builder()
            .coordinate(Coordinate.builder()
                .latitude(45.55)
                .longitude(23.23)
                .build())
            .address(address2)
            .build();

        Location location3 = Location.builder()
            .coordinate(Coordinate.builder()
                .latitude(35.45)
                .longitude(23.23)
                .build())
            .address(address3)
            .build();

        Accommodation accommodation1 = Accommodation.builder()
                .id(1L)
                .host(Host.builder()
                    .id(1L)
                    .email("test@test.com")
                    .phone("666")
                    .build())
                .name("Accommodation1")
                .maxNumberOfGuests(10)
                .location(location1)
                .build();

        Accommodation accommodation2 = Accommodation.builder()
                .id(2L)
                .host(Host.builder()
                    .id(2L)
                    .email("test2@test.com")
                    .phone("777")
                    .build())
                .name("Accommodation2")
                .location(location2)
                .maxNumberOfGuests(5)
                .build();

        Accommodation accommodation3 = Accommodation.builder()
                .id(3L)
                .host(Host.builder()
                    .id(3L)
                    .email("test3@test.com")
                    .phone("888")
                    .build())
                .name("Accommodation3")
                .location(location3)
                .maxNumberOfGuests(2)
                .build();

        accommodations.add(accommodation1);
        accommodations.add(accommodation2);
        accommodations.add(accommodation3);
    }

    @Test
    public void test_getAllAccommodationEndpoint_ShouldRunAndGetArrayFromService() throws Exception {
        String hostId = "1";
        when(service.getAllAccommodation(hostId)).thenReturn(accommodations);

        mockMvc.perform(MockMvcRequestBuilders
            .get("/get-all/{hostId}", 1))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].host.email").value("test@test.com"));

        verify(service, times(1)).getAllAccommodation(hostId);
    }
}