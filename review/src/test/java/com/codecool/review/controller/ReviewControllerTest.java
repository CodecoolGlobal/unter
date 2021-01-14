package com.codecool.review.controller;

import com.codecool.review.model.DTO.ReviewRequestDTO;
import com.codecool.review.model.DTO.ReviewResponseDTO;
import com.codecool.review.model.DTO.ReviewUpdateDTO;
import com.codecool.review.model.entity.Review;
import com.codecool.review.service.ReviewService;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ReviewController.class)
@ActiveProfiles("test")
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService service;

    private Review testReview;

    private ReviewRequestDTO testReviewRequestDTO;

    private ReviewResponseDTO testReviewResponseDTO;

    @BeforeEach
    public void setUp() {
        testReview = Review.builder()
            .accommodationId(1L)
            .guestId(1L)
            .rating(1.00)
            .message("testReview")
            .build();

        testReviewRequestDTO = ReviewRequestDTO.builder()
            .accommodationId(2L)
            .guestId(2L)
            .rating(1.00)
            .message("testReviewRequestDTO")
            .build();

        testReviewResponseDTO = ReviewResponseDTO.builder()
            .id(1L)
            .accommodationId(3L)
            .guestId(3L)
            .rating(1.00)
            .message("testReviewResponseDTO")
            .build();
    }

    @Test
    public void test_smokeTest() {
        assertThat(service).isNotNull();
    }

    @Test
    public void test_nonExistingEndpoint_ShouldNotBeFound() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/dummy")
                .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void test_findReviewByExistingIdEndpoint_shouldBeFound() throws Exception {
        when(service.findReviewById(1L)).thenReturn(testReviewResponseDTO);

        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/review-id/{reviewId}", 1L)
                .characterEncoding("utf-8"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
            .andExpect(MockMvcResultMatchers.jsonPath("$.accommodationId").value(3L))
            .andExpect(MockMvcResultMatchers.jsonPath("$.guestId").value(3L))
            .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(1.00))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("testReviewResponseDTO"))
            .andDo(print());

        verify(service, times(1)).findReviewById(1L);
    }

    @Test
    public void test_findAllReviewsEndpoint_shouldRunAndReturnArray() throws Exception {
        List<ReviewResponseDTO> reviews = new ArrayList<>();
        reviews.add(testReviewResponseDTO);

        when(service.findAllReviews()).thenReturn(reviews);

        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].accommodationId").value(3L))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].guestId").value(3L))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].rating").value(1.00))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("testReviewResponseDTO"));
    }

    @Test
    public void test_findAllReviewsByAccommodationIdEndpoint_shouldRunAndReturnArray() throws Exception {
        Long testId = 1L;
        List<ReviewResponseDTO> reviews = new ArrayList<>();
        reviews.add(testReviewResponseDTO);

        when(service.findAllReviewsByAccommodationId(testId)).thenReturn(reviews);

        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/accommodation-id/{accommodationId}", testId)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].accommodationId").value(3L))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].guestId").value(3L))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].rating").value(1.00))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("testReviewResponseDTO"));
    }

    @Test // todo
    public void test_findAllReviewsByNonExistingAccommodationId_shouldNotBeFound() throws Exception {
        when(service.findAllReviewsByAccommodationId(999L)).thenReturn(new ArrayList<>());

        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/accommodation-id/{accommodationId}", 999L)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void test_findAllReviewsNoGivenAccommodationId_shouldNotBeFound() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders
                .get("/accommodation-id/")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void test_saveNewReviewEndpoint_statusIsOk() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders
                .post("/")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testReviewRequestDTO)))
            .andExpect(status().isOk())
            .andDo(print());

        verify(service, times(1)).saveNewReview(any(ReviewRequestDTO.class));
    }

    @Test
    public void test_updateReviewEndpoint_statusIsOk() throws Exception {
        ReviewUpdateDTO testReviewUpdateDTO = ReviewUpdateDTO.builder()
            .message("update")
            .rating(5.00)
            .build();

        Long testId = 1L;
        mockMvc
            .perform(MockMvcRequestBuilders
                .put("/review-id/{reviewId}", testId)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testReviewUpdateDTO)))
            .andExpect(status().isOk())
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