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
    public List<ReviewResponseDTO> findAllReviews() {
        List<Review> reviews = repository.findAll();
        reviewResponseDTOList = new ArrayList<>();

        for (Review review : reviews) {
            ReviewResponseDTO newDTO = ReviewResponseDTO.builder()
                .id(review.getId())
                .accommodationId(review.getAccommodationId())
                .guestId(review.getGuestId())
                .rating(review.getRating())
                .message(review.getMessage())
                .date(review.getDate())
                .build();

            reviewResponseDTOList.add(newDTO);
        }

        return reviewResponseDTOList.stream()
            .filter(review -> review.getMessage() != null)
            .collect(Collectors.toList());
    }

    @Override
    public List<Review> findAllReviewsByAccommodationId(Long accommodationId) {
        return repository.findAllByAccommodationId(accommodationId);
    }
}
