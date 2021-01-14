package com.codecool.review.service;

import com.codecool.review.exception.NoDataFoundException;
import com.codecool.review.exception.ReviewNotFoundException;
import com.codecool.review.model.DTO.ReviewRequestDTO;
import com.codecool.review.model.DTO.ReviewResponseDTO;
import com.codecool.review.model.DTO.ReviewUpdateDTO;
import com.codecool.review.service.DAO.ReviewDAO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NullArgumentException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewDAO reviewDAO;

    public List<ReviewResponseDTO> findAllReviews() {
        if (reviewDAO.findAllReviews().isEmpty()) {
            throw new NoDataFoundException();
        }
        return reviewDAO.findAllReviews();
    }

    public List<ReviewResponseDTO> findAllReviewsByAccommodationId(Long accommodationId) {
        if (reviewDAO.findAllReviewsByAccommodationId(accommodationId).isEmpty()) {
            throw new NoDataFoundException();
        }
        return reviewDAO.findAllReviewsByAccommodationId(accommodationId);
    }

    public void saveNewReview(ReviewRequestDTO reviewRequestDTO) {
        reviewDAO.saveNewReview(reviewRequestDTO);
    }

    public ReviewResponseDTO findReviewById(Long reviewId) {
        return reviewDAO.findReviewById(reviewId);
    }

    public List<ReviewResponseDTO> findAllReviewsByGuestId(Long guestId) {
        if (reviewDAO.findAllReviewsByGuestId(guestId).isEmpty()) {
            throw new NoDataFoundException();
        }
        return reviewDAO.findAllReviewsByGuestId(guestId);
    }

    public void updateReview(Long reviewId, ReviewUpdateDTO reviewUpdateDTO) {
        try {
            reviewDAO.updateReview(reviewId, reviewUpdateDTO);
        } catch (NullArgumentException exception) {
            exception.printStackTrace();
        }
    }

    public void deleteReviewById(Long reviewId) {
        try {
            if (reviewDAO.reviewExistsById(reviewId)) {
                reviewDAO.deleteReviewById(reviewId);
            } else {
                throw new ReviewNotFoundException(reviewId);
            }
        } catch (NullArgumentException exception) {
            exception.printStackTrace();
        }
    }

    public void deleteAllReviewsByAccommodationId(Long accommodationId) {
        try {
            if (reviewDAO.existsByAccommodationId(accommodationId)) {
                reviewDAO.deleteAllReviewsByAccommodationId(accommodationId);
            } else {
                throw new NoDataFoundException();
            }
        } catch (NullArgumentException exception) {
            exception.printStackTrace();
        }
    }

    public void deleteAllReviewsByGuestId(Long guestId) {
        try {
            if (reviewDAO.existsByGuestId(guestId)) {
                reviewDAO.deleteAllReviewsByGuestId(guestId);
            } else {
                throw new NoDataFoundException();
            }
        } catch (NullArgumentException exception) {
            exception.printStackTrace();
        }
    }
}
