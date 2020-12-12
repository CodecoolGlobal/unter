package com.codecool.accommodation.model.wrapper;

import com.codecool.accommodation.model.DTO.AccommodationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccommodationDTOWrapper {
    private List<AccommodationDTO> AccommodationDTO;
}
