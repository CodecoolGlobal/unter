package com.codecool.review.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Double rating;

    @Column(nullable = false)
    private String message;

}
