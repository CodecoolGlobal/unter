package com.codecool.accommodation.service;

import com.codecool.accommodation.model.DTO.AccommodationDTO;
import com.codecool.accommodation.model.DTO.DTOWrapper;
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


    public DTOWrapper turnInputListToDTO(List<Accommodation> accommodations) {
        return new DTOWrapper(
                accommodations
                        .stream()
                        .map(this::converter)
                        .collect(Collectors.toList()));
    }

    private AccommodationDTO converter(Accommodation accommodation) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(accommodation, AccommodationDTO.class);
    }
}
