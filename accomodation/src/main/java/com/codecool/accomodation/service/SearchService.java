package com.codecool.accomodation.service;

import com.codecool.accomodation.model.Coordinates;
import com.codecool.accomodation.model.DTO.DTOWrapper;
import com.codecool.accomodation.model.entity.Accommodation;
import com.codecool.accomodation.model.entity.Location;
import com.codecool.accomodation.service.DAO.AccommodationDAO;
import com.codecool.accomodation.service.DAO.LocationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final DTOCreator creator;
    private final LocationDAO locationDAO;
    private final AccommodationDAO accommodationDAO;

    // TODO: think through whether we need to get accommodation twice from this list! (If we separate services,
    // the solution below might be needed. If not, it could be refactored
    public DTOWrapper getAllAccommodationInRadius(Coordinates coordinates, Double searchRadius) {
        List<Location> locationsInRadius = locationDAO.getLocationInDistance(coordinates, searchRadius);
        if (locationsInRadius.isEmpty()) {
            Accommodation emptyAccommodation = new Accommodation();
            return creator.turnInputListToDTO(Arrays.asList(emptyAccommodation));
        }
        List<Accommodation> allAccommodationsInRadius = new ArrayList<>();
        for (Location actualLocation : locationsInRadius) {
            allAccommodationsInRadius.add(actualLocation.getAccommodation());
        }
        return creator.turnInputListToDTO(allAccommodationsInRadius);
    }
}
