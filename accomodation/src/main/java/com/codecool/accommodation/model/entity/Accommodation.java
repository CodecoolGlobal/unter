package com.codecool.accommodation.model.entity;

import com.codecool.accommodation.model.entity.types.AccommodationType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(nullable = false)
    private Long hostId;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    private String pictureUrl;

    @Column(nullable = false)
    private Integer maxNumberOfGuests;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Location location;

    @Enumerated(EnumType.STRING)
    private AccommodationType type;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Singular
    @JsonBackReference
    private Set<Room> rooms;

}
