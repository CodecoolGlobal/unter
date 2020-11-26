package com.codecool.accomodation.model.entity;

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

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    private String description;

    @OneToOne(mappedBy = "location")
    @ToString.Exclude
    private Accommodation accommodation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
}
