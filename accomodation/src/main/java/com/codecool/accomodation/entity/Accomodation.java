package com.codecool.accomodation.entity;

import com.codecool.accomodation.types.AccomodationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Accomodation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private Host host;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccomodationType type;

    @OneToMany(mappedBy = "accomodation")
    @Column(nullable = false)
    private List<Room> rooms;

    private String picture;

    @Column(nullable = false)
    private Integer maxNumberOfGuests;

}
