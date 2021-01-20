package com.codecool.review.model.DTO;

import com.codecool.review.validation.annotation.ValidLong;
import com.codecool.review.validation.annotation.ValidMessage;
import com.codecool.review.validation.annotation.ValidRating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {

    @ValidLong(message = "Review Id cannot be null")
    private Long id;

    @ValidLong(message = "Accommodation Id cannot be null")
    private Long accommodationId;

    @ValidLong(message = "Guest / User Id cannot be null")
    private Long guestId;

    @ValidRating
    private Double rating;

    @ValidMessage
    private String message;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

}
