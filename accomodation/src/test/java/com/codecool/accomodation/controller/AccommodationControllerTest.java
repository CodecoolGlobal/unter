package com.codecool.accomodation.controller;

import com.codecool.accomodation.model.entity.Accommodation;
import com.codecool.accomodation.model.entity.Host;
import com.codecool.accomodation.service.AccommodationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = AccommodationController.class)
@ActiveProfiles("test")
class AccommodationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccommodationService service;

    private List<Accommodation> accommodations;

    @BeforeEach
    void setUp() {
        Accommodation accommodation1 = Accommodation.builder()
                .id(1L)
                .host(Host.builder().id(1L).build())
                .name("Accommodation1")
                .maxNumberOfGuests(10)
                .build();

        Accommodation accommodation2 = Accommodation.builder()
                .id(2L)
                .host(Host.builder().id(2L).build())
                .name("Accommodation2")
                .maxNumberOfGuests(5)
                .build();

        Accommodation accommodation3 = Accommodation.builder()
                .id(3L)
                .host(Host.builder().id(3L).build())
                .name("Accommodation3")
                .maxNumberOfGuests(2)
                .build();

        accommodations = Arrays.asList(accommodation1, accommodation2, accommodation3);
    }

    @Test
    public void test_getAllRooms() throws Exception {
        given(service.getAllAccommodation("1")).willReturn((List<Accommodation>) accommodations.get(0));
        long testId = 1L;

        this.mockMvc.perform(get("/get-all/" + testId))
//                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) testId)))
                .andExpect(jsonPath("$.name",  is("Accomodation1")))
                .andExpect(jsonPath("$.maxNumberOfGuests", is((int) 10)));
    }
}