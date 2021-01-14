package com.codecool.review.service.DAO;

import com.codecool.review.exception.ReviewNotFoundException;
import com.codecool.review.model.DTO.ReviewRequestDTO;
import com.codecool.review.model.DTO.ReviewResponseDTO;
import com.codecool.review.model.DTO.ReviewUpdateDTO;
import com.codecool.review.model.entity.Review;
import com.codecool.review.repository.ReviewRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
@RequiredArgsConstructor
public class ReviewDB implements ReviewDAO {

    private final ReviewRepository repository;

    private List<ReviewResponseDTO> reviewResponseDTOList;

    @Override
    public List<Review> findAllReviews() {
        return repository.findAll();
    }

    @Override
    public List<Review> findAllReviewsByAccommodationId(Long accommodationId) {
        return repository.findAllByAccommodationId(accommodationId);
    }
}
