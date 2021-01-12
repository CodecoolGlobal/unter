package com.codecool.review.service;

import com.codecool.review.exception.NoDataFoundException;
import com.codecool.review.model.entity.Review;
import com.codecool.review.service.DAO.ReviewDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewDAO reviewDAO;

    public List<Review> findAllReviews() {
        return reviewDAO.findAllReviews();
    }

    public List<Review> findAllReviewsByAccommodationId(Long accommodationId) {
        if (reviewDAO.findAllReviewsByAccommodationId(accommodationId) == null) {
            throw new NoDataFoundException();
        }
        return reviewDAO.findAllReviewsByAccommodationId(accommodationId);
    }
}
