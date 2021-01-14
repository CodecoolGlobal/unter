package com.codecool.accommodation.model.entity;

import com.codecool.accommodation.model.entity.types.AccommodationType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
//@NoArgsConstructor
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

    @Column(columnDefinition="text")
    private String description;

    @Column
    private Double rating;

   /* @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String pictureUrl;
  */

    @ElementCollection
    private List<String> pictures;

    @Column(nullable = false)
    private Integer maxNumberOfGuests;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Coordinate coordinate;

    @Enumerated(EnumType.STRING)
    private AccommodationType type;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Set<Room> rooms;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Address address;

    public Accommodation() {

    }

    public void createRooms(){
        this.rooms = new HashSet<>();
    }
}
