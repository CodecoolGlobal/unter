package com.codecool.review.repository;

import com.codecool.review.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByAccommodationId(Long id);
    List<Review> findAllByGuestId(Long id);
    void deleteAllByAccommodationId(Long accommodationId);
    void deleteAllByGuestId(Long guestId);
    boolean existsByAccommodationId(Long accommodationId);
    boolean existsByGuestId(Long guestId);
}
