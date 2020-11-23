package com.codecool.accomodation.entity;

import com.codecool.accomodation.types.BedType;
import com.codecool.accomodation.types.RoomType;
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

    @Column(nullable = false)
    private RoomType type;

    @ElementCollection
    @CollectionTable(name = "bed_quantity",
            joinColumns = { @JoinColumn(name = "room_id") })
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "quantity")
    private Map<BedType, Integer> beds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_id")
    private Accomodation accomodation;

}
