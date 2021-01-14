package com.codecool.review.controller;

import com.codecool.review.model.DTO.ReviewRequestDTO;
import com.codecool.review.model.DTO.ReviewResponseDTO;
import com.codecool.review.model.DTO.ReviewUpdateDTO;
import com.codecool.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @GetMapping
    public List<ReviewResponseDTO> getAll() {
        return service.findAllReviews();
    }

    @GetMapping("/review-id/{reviewId}")
    public ReviewResponseDTO getReviewById(@PathVariable(name = "reviewId") Long reviewId) {
        return service.findReviewById(reviewId);
    }

    @GetMapping("/accommodation-id/{accommodationId}")
    public List<ReviewResponseDTO> getAllReviewByAccommodationId(@PathVariable(name = "accommodationId") Long accommodationId) {
        return service.findAllReviewsByAccommodationId(accommodationId);
    }
}
