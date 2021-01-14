package com.codecool.review.controller;

import com.codecool.review.model.entity.Review;
import com.codecool.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @GetMapping
    public List<ReviewResponseDTO> getAll() {
        return service.findAllReviews();
    }

    @GetMapping("/accommodation-id/{accommodationId}")
    public List<Review> getAllReviewByAccommodationId(@PathVariable(name = "accommodationId") Long accommodationId) {
        return service.findAllReviewsByAccommodationId(accommodationId);
    }
}
