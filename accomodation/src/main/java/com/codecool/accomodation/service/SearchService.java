package com.codecool.accomodation.service;

import com.codecool.accomodation.model.DTO.CoordinateDTO;
import com.codecool.accomodation.model.DTO.DTOWrapper;
import com.codecool.accomodation.model.entity.Accommodation;
import com.codecool.accomodation.model.entity.Coordinate;
import com.codecool.accomodation.service.DAO.CoordinateDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final DTOCreator creator;
    private final CoordinateDAO coordinateDAO;

    public DTOWrapper getAllAccommodationInRadius(CoordinateDTO coordinate, Double searchRadius) {
        List<Coordinate> coordinatesWithinDistance = coordinateDAO.getAllByDistanceFromCoordinate(coordinate, searchRadius);
        if (coordinatesWithinDistance.isEmpty()) return creator.turnInputListToDTO(Arrays.asList(new Accommodation()));

        List<Accommodation> allAccommodationsInRadius = new ArrayList<>();
        for (Coordinate actualLocation : coordinatesWithinDistance) {
            allAccommodationsInRadius.add(actualLocation.getLocation().getAccommodation());
        }
        return creator.turnInputListToDTO(allAccommodationsInRadius);
    }
}