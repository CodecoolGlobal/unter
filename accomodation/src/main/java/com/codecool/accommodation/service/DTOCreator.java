package com.codecool.accommodation.service;

import com.codecool.accommodation.model.DTO.NewAccommodationDTO;
import com.codecool.accommodation.model.wrapper.NewAccommodationDTOWrapper;
import com.codecool.accommodation.model.entity.Accommodation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DTOCreator {

    private final ModelMapper modelMapper;


    public NewAccommodationDTOWrapper turnInputListToDTO(List<Accommodation> accommodations) {
        return new NewAccommodationDTOWrapper(
                accommodations
                        .stream()
                        .map(this::converter)
                        .collect(Collectors.toList()));
    }

    private NewAccommodationDTO converter(Accommodation accommodation) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(accommodation, NewAccommodationDTO.class);
    }
}
