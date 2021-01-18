package com.codecool.review.model.DTO;

import com.codecool.review.validation.annotation.ValidMessage;
import com.codecool.review.validation.annotation.ValidRating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUpdateDTO {

    @ValidRating
    private Double rating;

    @ValidMessage
    private String message;
}
