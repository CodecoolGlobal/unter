package com.codecool.review.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {

    private Long id;
    private Long accommodationId;
    private Long guestId;
    private Double rating;
    private String message;
    private LocalDate date;

}
