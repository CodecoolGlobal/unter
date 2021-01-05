package com.codecool.accommodation.model.entity;

import com.codecool.accommodation.model.entity.types.BedType;
import com.codecool.accommodation.model.entity.types.RoomType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.MapKeyType;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    private RoomType type;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "bed_quantity",
        joinColumns = { @JoinColumn(name = "room_id") })
    @MapKeyEnumerated(EnumType.ORDINAL)
    @MapKeyClass(BedType.class)
    @JsonBackReference
    private Map<BedType, Integer> beds;

    @ManyToOne
    private Accommodation accommodation;

    public Room(RoomType type) {
        this.type = type;
    }

    public Room() {

    }


    public void setAccommodation(Accommodation accommodation) {
        this.accommodation= accommodation;
        accommodation.getRooms().add(this);
    }


}
