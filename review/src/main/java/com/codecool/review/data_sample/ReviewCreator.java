package com.codecool.review.data_sample;

import com.codecool.review.model.entity.Review;
import com.codecool.review.repository.ReviewRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ReviewCreator {

    private final ReviewRepository repository;

    public ReviewCreator(ReviewRepository repository) {
        this.repository = repository;
    }

    public void initialize() {
        Long[] accommodationIds = {1L, 4L, 4L, 4L, 7L, 10L, 13L, 16L, 16L};
        Long[] guestIds = {1L, 1L, 2L, 3L, 5L, 5L, 7L, 8L, 8L};
        Double[] ratings = {1.0, 5.0, 4.0, 5.0, 2.0, 3.0, 5.0, 4.0, 3.0};
        String[] messages = {
            "It was hard to find the address. The sheets were dirty and we found uncleaned dishes in the kitchen. I wouldn't recommend to stay here. Very disappointing.",
            "Beautiful neighbourhood, even the neighbours were  very cool. The apartment is super cute and clean. Highly recommended!",
            "Everything was perfect except the wifi. But the apartment and the neighbourhood was amazing. Budapest is beautiful and people are super nice!",
            "Loved it! Next year we are gonna visit Budapest again for sure! Hopefully we can stay here again:)",
            "The place was a bit far from the center and the bed was uncomfortable. Budapest is nice but many people dont speak English. We were a bit disappointed.",
            "We had some issues with the wifi and the bathroom was dirty and got cleaned after we arrived which was a bit weird. But we had fun and the location of the place is very close to everything.",
            "Very cool people, very cool apartment. Budapest is awesome!!!",
            "Loved the place, the neighbourhood and Budapest. The neighbours were very loud, tho.",
            "The location is perfect but it was hard to find because there is no sign on the building. The place is nice but rotten food was left in the fridge. Pay extra attention to these issues, and we come back next time!"
        };

        for (int i = 0; i < messages.length; i++) {
            NewReviewSample newReviewSample = NewReviewSample.builder()
                .accommodationId(accommodationIds[i])
                .guestId(guestIds[i])
                .rating(ratings[i])
                .message(messages[i])
                .date(generateDate())
                .build();

            repository.saveAndFlush(createReview(newReviewSample));
        }
    }
    public  LocalDate generateDate() {
        long startEpochDay = LocalDate.of(2018, 1, 1).toEpochDay();
        long endEpochDay = (LocalDate.now()).toEpochDay();
        long randomDay = ThreadLocalRandom
            .current()
            .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    public Review createReview(NewReviewSample newReviewSample) {
        return Review.builder()
            .accommodationId(newReviewSample.getAccommodationId())
            .guestId(newReviewSample.getGuestId())
            .rating(newReviewSample.getRating())
            .message(newReviewSample.getMessage())
            .date(newReviewSample.getDate())
            .build();
    }
}
