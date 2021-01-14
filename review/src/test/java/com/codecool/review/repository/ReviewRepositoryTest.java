package com.codecool.review.repository;

import com.codecool.review.model.entity.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    private Review testReview;

    @BeforeEach
    void setUp() {
        testReview = Review.builder()
            .accommodationId(1L)
            .guestId(1L)
            .message("Test Review")
            .rating(5.00)
            .build();
    }

    @Test
    public void smokeTest() {
        assertThat(reviewRepository).isNotNull();
    }

    @Test
    public void test_emptyDb_isEmpty() {
        List<Review> reviews = reviewRepository.findAll();
        assertThat(reviews).isEmpty();
    }

    @Test
    public void test_saveNewReview_hasSizeOne() {
        reviewRepository.saveAndFlush(testReview);

        // test if db size increased with one and contains the saved accommodation
        List<Review> reviews = reviewRepository.findAll();
        assertThat(reviews).hasSize(1);
        assertThat(reviews).contains(testReview);

        // test if found accommodation's name is the same as the the one's that was saved
        Review review = reviews.get(0);
        assertThat(review).isNotNull();
        assertEquals(review.getMessage(), testReview.getMessage());
        assertEquals(review.getRating(), testReview.getRating());
        assertEquals(review.getAccommodationId(), testReview.getAccommodationId());
    }

    @Test
    public void test_findReviewById_shouldBeFound() {
        Long id = reviewRepository.saveAndFlush(testReview).getId();
        Review found = reviewRepository.findById(id).get();

        assertEquals(found.getMessage(), testReview.getMessage());
        assertEquals(found.getAccommodationId(), testReview.getAccommodationId());

        assertThat(found).isEqualTo(testReview);
    }

    @Test
    public void test_findReviewsByNonExistingId_isEmpty() {
        Long testId = 666L;
        reviewRepository.saveAndFlush(testReview);

        // save testReview with hostId = 1L
        reviewRepository.saveAndFlush(testReview);

        // find all by the testId
        List<Review> foundByHostId = reviewRepository.findAllByAccommodationId(testId);

        // test
        assertThat(foundByHostId).hasSize(0);
        assertThat(foundByHostId).isEmpty();
        assertThat(foundByHostId).doesNotContain(testReview);
    }

    @Test
    public void test_saveSeveralReviews_persistAll() {
        // build another review
        Review testReview2 = Review.builder()
            .accommodationId(1L)
            .guestId(1L)
            .message("Test Review")
            .rating(10.00)
            .build();

        // save reviews and get all from db
        List<Review> newReviews = Arrays.asList(testReview, testReview2);
        reviewRepository.saveAll(newReviews);
        List<Review> reviews = reviewRepository.findAll();

        // test
        assertThat(reviews).hasSize(2);
        assertThat(reviews).contains(testReview, testReview2);
    }

    @Test
    public void test_reviewAccommodationIdShouldBeNotBull_throwsException() {
        // build review
        Review review = Review.builder()
            // missing accommodation id field
            .guestId(1L)
            .message("Test Review")
            .rating(10.00)
            .build();

        // test
        assertThrows(DataIntegrityViolationException.class,
            () -> reviewRepository.saveAndFlush(review));
    }

    @Test
    public void test_reviewGuestIdShouldBeNotBull_throwsException() {
        // build review
        Review review = Review.builder()
            .accommodationId(1L)
            // missing guestId field
            .message("Test Review")
            .rating(5.00)
            .build();

        // test
        assertThrows(DataIntegrityViolationException.class,
            () -> reviewRepository.saveAndFlush(review));
    }

    @Test
    public void test_reviewMessageCanBeNull_throwsException() {
        // build review
        Review review = Review.builder()
            .accommodationId(1L)
            .guestId(1L)
            // missing message field
            .rating(10.00)
            .build();

        reviewRepository.save(review);
        List<Review> reviews = reviewRepository.findAll();
        assertThat(reviews).contains(review);
        assertThat(reviews).hasSize(1);
    }

    @Test
    public void test_reviewRatingShouldBeNotBull_persistProperly() {
        // build review
        Review review = Review.builder()
            .accommodationId(1L)
            .guestId(1L)
            .message("Test Review")
            // missing rating field
            .build();

        // test
        assertThrows(DataIntegrityViolationException.class,
            () -> reviewRepository.saveAndFlush(review));
    }

    @Test
    public void test_reviewUpdated_fieldsAreChanged() {
        // saved review
        reviewRepository.saveAndFlush(testReview);
        Review reviewToEdit = reviewRepository.findAll().get(0);

        // update fields
        reviewToEdit.setMessage("updated message");
        reviewToEdit.setRating(1.00);

        // save updated review
        reviewRepository.save(reviewToEdit);
        Review updated = reviewRepository.findById(reviewToEdit.getId()).get();

        // test
        assertEquals(updated.getId(), reviewToEdit.getId());
        assertEquals(updated.getMessage(), "updated message");
        assertEquals(updated.getRating(), 1.00);
    }

    @Test
    public void test_deleteReviewById_notFound() {
        // saved review
        reviewRepository.saveAndFlush(testReview);
        Review reviewToDelete = reviewRepository.findAll().get(0);

        // delete review by id
        reviewRepository.deleteById(reviewToDelete.getId());

        // test if accommodation list got increased with one
        List<Review> reviews = reviewRepository.findAll();
        assertThat(reviews).hasSize(0);
        assertThat(reviews).isEmpty();

        // test accommodation was not found by id
        assertThrows(NoSuchElementException.class,
            () -> reviewRepository.findById(reviewToDelete.getId()).get());
    }

    @Test
    public void test_deleteAllReview_shouldBeEmpty() {
        // build another review
        Review testReview2 = Review.builder()
            .accommodationId(1L)
            .guestId(1L)
            .message("Test Review")
            .rating(10.00)
            .build();

        // save reviews
        reviewRepository.saveAll(Arrays.asList(testReview, testReview2));

        // delete all
        reviewRepository.deleteAll();

        // test
        List<Review> reviews = reviewRepository.findAll();
        assertThat(reviews).isEmpty();
        assertThat(reviews).doesNotContain(testReview, testReview2);
    }
}