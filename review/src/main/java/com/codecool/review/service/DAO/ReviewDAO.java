package com.codecool.review.service.DAO;

import com.codecool.review.model.DTO.ReviewRequestDTO;
import com.codecool.review.model.DTO.ReviewResponseDTO;
import com.codecool.review.model.DTO.ReviewUpdateDTO;
import java.util.List;

public interface ReviewDAO {

    List<ReviewResponseDTO> findAllReviews();
    List<ReviewResponseDTO> findAllReviewsByAccommodationId(Long accommodationId);
    void saveNewReview(ReviewRequestDTO reviewRequestDTO);
    ReviewResponseDTO findReviewById(Long reviewId);
    List<ReviewResponseDTO> findAllReviewsByGuestId(Long guestId);
    void updateReview(Long reviewId, ReviewUpdateDTO reviewUpdateDTO);
    boolean reviewExistsById(Long reviewId);
    void deleteReviewById(Long reviewId);
    void deleteAllReviewsByAccommodationId(Long accommodationId);
    void deleteAllReviewsByGuestId(Long guestId);
    boolean existsByAccommodationId(Long accommodationId);
    boolean existsByGuestId(Long guestId);
}
