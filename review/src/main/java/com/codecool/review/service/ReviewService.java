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
}
