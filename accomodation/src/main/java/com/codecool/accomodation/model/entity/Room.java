package com.codecool.accomodation.model.entity;

import com.codecool.accomodation.model.entity.types.BedType;
import com.codecool.accomodation.model.entity.types.RoomType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    private RoomType type;

    @ElementCollection
    @CollectionTable(name = "bed_quantity",
        joinColumns = { @JoinColumn(name = "room_id") })
    @MapKeyEnumerated(EnumType.STRING)
    @JsonBackReference
    private Map<BedType, Integer> beds;

    @ManyToOne
    private Accommodation accommodation;
}
