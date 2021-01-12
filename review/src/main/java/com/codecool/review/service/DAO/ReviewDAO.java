package com.codecool.review.service.DAO;

import com.codecool.review.model.entity.Review;

import java.util.List;

public interface ReviewDAO {

    List<Review> findAllReviews();
    List<Review> findAllReviewsByAccommodationId(Long accommodationId);
}
