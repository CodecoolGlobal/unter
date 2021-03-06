package com.codecool.accommodation.model.wrapper;

import com.codecool.accommodation.model.DTO.NewAccommodationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NewAccommodationDTOWrapper {

    private List<NewAccommodationDTO> newAccommodationDTOS;
}
