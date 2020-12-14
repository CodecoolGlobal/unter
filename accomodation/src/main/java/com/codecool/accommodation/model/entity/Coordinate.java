package com.codecool.accommodation.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @OneToOne(mappedBy = "coordinate")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Accommodation accommodation;

//    @OneToOne
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @JsonBackReference
//    private Location location;

    public Coordinate(Double longitude, Double latitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
