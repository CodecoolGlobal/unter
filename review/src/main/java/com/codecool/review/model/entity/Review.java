package com.codecool.review.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long accommodationId;

    @Column(nullable = false)
    private Long guestId;

    @Column(nullable = false)
    @Min(value = 1, message = "Rating must be equal or greater than 1")
    @Max(value = 5, message = "Rating must be equal or less than 5")
    private Double rating;

    @Column(columnDefinition = "text", length = 255)
    private String message;

    @Column
    private LocalDate date;

}
