package com.codecool.review.data_sample;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class NewReviewSample {
    private Long accommodationId;
    private Long guestId;
    private Double rating;
    private String message;
    private LocalDate date;
}
