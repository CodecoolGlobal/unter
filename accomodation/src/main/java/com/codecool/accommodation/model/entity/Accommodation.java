package com.codecool.accommodation.model.entity;

import com.codecool.accommodation.model.entity.types.AccommodationType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", nullable = false)
    @ToString.Exclude
    @JsonManagedReference
    private Location location;

    @Enumerated(EnumType.STRING)
    private AccommodationType type;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Singular
    private Set<Room> rooms;

    @Column(nullable = false)
    private Integer maxNumberOfGuests;

}
