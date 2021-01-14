package com.codecool.review.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequestDTO {

    private Long accommodationId;
    private Long guestId;
    private Double rating;
    private String message;

    public ReviewRequestDTO() {
    }

    public ReviewRequestDTO(Long accommodationId, Long guestId, Double rating, String message) {
        this.accommodationId = accommodationId;
        this.guestId = guestId;
        this.rating = rating;
        this.message = message;
    }
}
