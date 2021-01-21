package com.codecool.review.controller;

import com.codecool.review.model.DTO.ReviewRequestDTO;
import com.codecool.review.model.DTO.ReviewResponseDTO;
import com.codecool.review.model.DTO.ReviewUpdateDTO;
import com.codecool.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public void saveNewReview(@RequestBody @Valid ReviewRequestDTO reviewRequestDTO) {
        service.saveNewReview(reviewRequestDTO);
    }

    @GetMapping("/guest-id/{guestId}")
    public List<ReviewResponseDTO> getAllReviewsByGuestId(@PathVariable(name = "guestId") Long guestId) {
        return service.findAllReviewsByGuestId(guestId);
    }

    @PutMapping("/review-id/{reviewId}")
    public void updateReview(@PathVariable(name = "reviewId") Long reviewId, @RequestBody @Valid ReviewUpdateDTO reviewUpdateDTO) {
        service.updateReview(reviewId, reviewUpdateDTO);
    }

    @DeleteMapping("/review-id/{reviewId}")
    public void deleteReviewById(@PathVariable(name = "reviewId") Long reviewId) {
        service.deleteReviewById(reviewId);
    }

    @DeleteMapping("/accommodation-id/{accommodationId}")
    public void deleteAllReviewsByAccommodationId(@PathVariable(name = "accommodationId") Long accommodationId) {
        service.deleteAllReviewsByAccommodationId(accommodationId);
    }

    @DeleteMapping("/guest-id/{guestId}")
    public void deleteAllReviewsByGuestId(@PathVariable(name = "guestId") Long guestId) {
        service.deleteAllReviewsByGuestId(guestId);
    }
    
    @GetMapping("/rating-avg/{accommodationId}")
    public Double getAccommodationAverageRating(@PathVariable(name = "accommodationId") Long accommodationId) {
        return service.getAverageRating(accommodationId);
    }
}
