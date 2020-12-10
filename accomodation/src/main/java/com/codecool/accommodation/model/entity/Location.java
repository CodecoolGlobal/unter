package com.codecool.accommodation.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Location {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_id", nullable = false)
    @JsonManagedReference
    private Coordinate coordinate;

    private String description;

    @OneToOne(mappedBy = "location")
    @ToString.Exclude
    private Accommodation accommodation;


}
