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
    public List<ReviewResponseDTO> findAllReviewsByAccommodationId(Long accommodationId) {
        List<Review> reviews = repository.findAllByAccommodationId(accommodationId);
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
        return reviewResponseDTOList.stream() // list only the ones that have message field
            .filter(review -> review.getMessage() != null)
            .collect(Collectors.toList());
    }

    @Override
    public void saveNewReview(ReviewRequestDTO reviewRequestDTO) {
        Review review = new Review();

        review.setGuestId(reviewRequestDTO.getGuestId());
        review.setAccommodationId(reviewRequestDTO.getAccommodationId());
        review.setRating(reviewRequestDTO.getRating());
        review.setMessage(reviewRequestDTO.getMessage());
        review.setDate(LocalDate.now());

        repository.save(review);
    }

    @Override
    public ReviewResponseDTO findReviewById(Long reviewId) {
        Review review = repository.findById(reviewId)
            .orElseThrow(() -> new ReviewNotFoundException(reviewId));

        return ReviewResponseDTO.builder()
            .id(review.getId())
            .accommodationId(review.getAccommodationId())
            .guestId(review.getGuestId())
            .rating(review.getRating())
            .message(review.getMessage())
            .date(review.getDate())
            .build();
    }

    @Override
    public List<ReviewResponseDTO> findAllReviewsByGuestId(Long guestId) {
        List<Review> reviews = repository.findAllByGuestId(guestId);
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
        return reviewResponseDTOList; // all reviews message null included
    }

    @Override
    public void updateReview(Long reviewId, ReviewUpdateDTO reviewUpdateDTO) {
        Review reviewToEdit = repository.findById(reviewId)
            .orElseThrow(() -> new ReviewNotFoundException(reviewId));

        reviewToEdit.setRating(reviewUpdateDTO.getRating());
        reviewToEdit.setMessage(reviewUpdateDTO.getMessage());
        reviewToEdit.setDate(LocalDate.now());

        repository.save(reviewToEdit);
    }

    @Override
    public boolean reviewExistsById(Long reviewId) {
        return repository.existsById(reviewId);
    }

    @Override
    public void deleteReviewById(Long reviewId) {
        repository.deleteById(reviewId);
    }

    @Override
    @Transactional
    public void deleteAllReviewsByAccommodationId(Long accommodationId) {
        repository.deleteAllByAccommodationId(accommodationId);
    }

    @Override
    @Transactional
    public void deleteAllReviewsByGuestId(Long guestId) {
        repository.deleteAllByGuestId(guestId);
    }

    @Override
    public boolean existsByAccommodationId(Long accommodationId) {
        return repository.existsByAccommodationId(accommodationId);
    }

    @Override
    public boolean existsByGuestId(Long guestId) {
        return repository.existsByGuestId(guestId);
    }
}
