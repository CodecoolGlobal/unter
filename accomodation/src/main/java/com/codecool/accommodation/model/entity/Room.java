package com.codecool.accommodation.model.entity;

import com.codecool.accommodation.model.entity.types.BedType;
import com.codecool.accommodation.model.entity.types.RoomType;
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
    private Long id;

    private RoomType type;

//    @ElementCollection
//    @CollectionTable(name = "bed_quantity",
//        joinColumns = { @JoinColumn(name = "room_id") })
//    @MapKeyEnumerated(EnumType.STRING)
//    @JsonBackReference
//    private Map<BedType, Integer> beds;

    @ManyToOne
    private Accommodation accommodation;

    public Room(Long i, RoomType type) {
        this.id = i;
        this.type = type;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation= accommodation;
        accommodation.getRooms().add(this);
    }


}
