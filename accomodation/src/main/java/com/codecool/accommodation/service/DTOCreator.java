package com.codecool.accommodation.service;

import com.codecool.accommodation.model.DTO.AccommodationDTO;
import com.codecool.accommodation.model.DTO.CoordinateDTO;
import com.codecool.accommodation.model.DTO.NewAccommodationDTO;
import com.codecool.accommodation.model.wrapper.AccommodationDTOWrapper;
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

    public NewAccommodationDTOWrapper turnInputListToNewAccommodationDTO(List<Accommodation> accommodations) {
        return new NewAccommodationDTOWrapper(
                accommodations
                        .stream()
                        .map(this::convertToNewAccommodationDTO)
                        .collect(Collectors.toList()));
    }

    public AccommodationDTOWrapper turnInputListToAccommodationDTO(List<Accommodation> accommodations) {
        return new AccommodationDTOWrapper(
                accommodations
                        .stream()
                        .map(this::convertToAccommodationDTO)
                        .collect(Collectors.toList()));
    }

    private NewAccommodationDTO convertToNewAccommodationDTO(Accommodation accommodation) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(accommodation, NewAccommodationDTO.class);
    }

    private AccommodationDTO convertToAccommodationDTO(Accommodation accommodation) {
        CoordinateDTO coordinateDTO = (accommodation.getCoordinate() == null)
                ? new CoordinateDTO(null, null)
                : new CoordinateDTO(accommodation.getCoordinate().getLatitude(), accommodation.getCoordinate().getLongitude());

        return AccommodationDTO.builder()
                .id(accommodation.getId())
                .hostId(accommodation.getHostId())
                .accommodationName(accommodation.getName())
                .description(accommodation.getDescription())
                .pictures(accommodation.getPictures())
                .capacity(accommodation.getMaxNumberOfGuests()) // TODO: implement calculate capacity
                .numberOfRooms(accommodation.getRooms().size())
//                .numberOfBeds() // TODO: implement calculate number of beds
//                .numberOfBathrooms() // TODO: implement caclulate number of bathrooms
                .coordinates(coordinateDTO)
                .address(accommodation.getAddress())
                .build();

    }
}
