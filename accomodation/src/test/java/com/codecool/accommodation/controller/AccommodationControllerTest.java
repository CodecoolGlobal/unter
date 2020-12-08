package com.codecool.accommodation.controller;

import com.codecool.accommodation.model.DTO.AccommodationDTO;
import com.codecool.accommodation.model.entity.*;
import com.codecool.accommodation.service.AccommodationService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class AccommodationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccommodationService service;

    @Test // do we need this, if it's mocked?
    public void smokeTest() {
        assertThat(service).isNotNull();
    }

    @Test
    public void test_getAllAccommodationEndpoint_ShouldRunAndGetArrayFromService() throws Exception {
        Address address1 = Address.builder()
            .houseNumber(2)
            .street("Érc utca")
            .city("Budapest")
            .zipCode("1032")
            .build();

        Location location1 = Location.builder()
            .coordinate(Coordinate.builder()
                .latitude(45.45)
                .longitude(23.23)
                .build())
            .address(address1)
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

        List<Accommodation> accommodations = new ArrayList<>();
        accommodations.add(accommodation1);

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