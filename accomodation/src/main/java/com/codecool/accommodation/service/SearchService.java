package com.codecool.accommodation.service;

import com.codecool.accommodation.model.DTO.AccommodationDTO;
import com.codecool.accommodation.model.DTO.CoordinateDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.model.wrapper.AccommodationDTOWrapper;
import com.codecool.accommodation.service.DAO.CoordinateDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final DTOCreator creator;
    private final CoordinateDAO coordinateDAO;

    public ResponseEntity<?> getAccommodationsInRadius(CoordinateDTO coordinate, Double searchRadius) {
        List<Coordinate> foundCoordinates = coordinateDAO.getAllByDistanceFromCoordinate(coordinate, searchRadius);
        if (foundCoordinates.isEmpty())
            return ResponseEntity.ok(
                    new AccommodationDTOWrapper(Collections.singletonList(
                            AccommodationDTO.builder()
                                    .coordinates(new CoordinateDTO(null, null))
                                    .build())
                    )
            );

        List<Accommodation> allAccommodationsInRadius = new ArrayList<>();
        for (Coordinate actualCoordinate : foundCoordinates) {
            allAccommodationsInRadius.add(actualCoordinate.getAccommodation());
        }
        return ResponseEntity.ok(creator.turnInputListToAccommodationDTO(allAccommodationsInRadius));
    }
}
