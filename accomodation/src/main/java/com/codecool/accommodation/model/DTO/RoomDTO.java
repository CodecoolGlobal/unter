package com.codecool.accommodation.model.DTO;

import com.codecool.accommodation.model.entity.types.RoomType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class RoomDTO {

    private RoomType type;


}
