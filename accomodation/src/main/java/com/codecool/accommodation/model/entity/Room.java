package com.codecool.accommodation.model.entity;

import com.codecool.accommodation.model.entity.types.BedType;
import com.codecool.accommodation.model.entity.types.RoomType;
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
    private Map<BedType, Integer> beds;

    @ManyToOne
    private Accommodation accommodation;
}
