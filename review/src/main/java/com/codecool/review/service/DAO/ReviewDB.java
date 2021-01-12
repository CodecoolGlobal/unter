package com.codecool.review.service.DAO;

import com.codecool.review.model.entity.Review;
import com.codecool.review.repository.ReviewRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class ReviewDB implements ReviewDAO {

    private final ReviewRepository repository;

    @Override
    public List<Review> findAllReviews() {
        return repository.findAll();
    }

    @Override
    public List<Review> findAllReviewsByAccommodationId(Long accommodationId) {
        return repository.findAllByAccommodationId(accommodationId);
    }
}
