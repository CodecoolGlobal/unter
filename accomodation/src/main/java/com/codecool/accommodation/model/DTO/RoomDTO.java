package com.codecool.accommodation.model.DTO;

import com.codecool.accommodation.model.entity.types.RoomType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDTO {

    private Long id;

    private RoomType type;
}
