package com.codecool.review.model.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReviewDTO {
    public Long accId;
    public Double rating;
    public List<ReviewResponseDTO> reviews;

    @JsonCreator
    public ReviewDTO(@JsonProperty("accId") Long accId, @JsonProperty("rating") Double rating, @JsonProperty("reviews") List<ReviewResponseDTO> reviews) {
        this.accId = accId;
        this.rating = rating;
        this.reviews = reviews;
    }
}
