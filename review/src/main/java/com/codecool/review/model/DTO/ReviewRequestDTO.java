package com.codecool.review.model.DTO;

import com.codecool.review.validation.annotation.ValidLong;
import com.codecool.review.validation.annotation.ValidMessage;
import com.codecool.review.validation.annotation.ValidRating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDTO {

    @ValidLong(message = "Accommodation id cannot be null")
    private Long accommodationId;

    @ValidLong(message = "Guest / User id cannot be null")
    private Long guestId;

    @ValidRating
    private Double rating;

    @ValidMessage
    private String message;
}
