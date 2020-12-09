package com.codecool.accommodation.service;

import com.codecool.accommodation.model.DTO.CoordinateDTO;
import com.codecool.accommodation.model.DTO.DTOWrapper;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.model.entity.Location;
import com.codecool.accommodation.service.DAO.CoordinateDAO;
import com.codecool.accommodation.service.DAO.LocationDAO;
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
    private final LocationDAO locationDAO;

    public DTOWrapper getAllAccommodationInRadius(CoordinateDTO coordinate, Double searchRadius) {
        List<Coordinate> coordinatesWithinDistance = coordinateDAO.getAllByDistanceFromCoordinate(coordinate, searchRadius);
        if (coordinatesWithinDistance.isEmpty()) return creator.turnInputListToDTO(Arrays.asList(new Accommodation()));

        List<Accommodation> allAccommodationsInRadius = new ArrayList<>();
        System.out.println(coordinatesWithinDistance);
        for (Coordinate actualCoordinate : coordinatesWithinDistance) {
            Location actualLocation = locationDAO.getLocationByCoordinate(actualCoordinate);

            allAccommodationsInRadius.add(actualLocation.getAccommodation());
        }
        return creator.turnInputListToDTO(allAccommodationsInRadius);
    }


}
