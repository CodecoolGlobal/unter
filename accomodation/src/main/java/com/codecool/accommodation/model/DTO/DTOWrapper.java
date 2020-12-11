package com.codecool.accommodation.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DTOWrapper {

    private List<AccommodationDTO> accommodationDTOS;
}
