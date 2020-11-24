package com.codecool.accomodation.entity;

import com.codecool.accomodation.entity.types.AccommodationType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Host host;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @Enumerated(EnumType.STRING)
    private AccommodationType type;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Room> rooms;

    @Column(nullable = false)
    private Integer maxNumberOfGuests;

}
