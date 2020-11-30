package com.codecool.accomodation.service;

import com.codecool.accomodation.model.entity.Accommodation;
import com.codecool.accomodation.model.entity.Location;
import com.codecool.accomodation.service.DAO.AccommodationDAO;
import com.codecool.accomodation.service.DAO.LocationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final LocationDAO locationDAO;
    private final AccommodationDAO accommodationDAO;

    // TODO: think through whether we need to get accommodation twice from this list! (If we separate services,
    // the solution below might be needed. If not, it could be refactored
    public List<Accommodation> getAllAccommodationInRadius(Double longitude, Double latitude, Double radius) {
        List<Location> locationsInRadius = locationDAO.getLocationInDistance(latitude, longitude, radius);
        List<Accommodation> allAccommodationsInRadius = new ArrayList<>();
        for (Location actualLocation : locationsInRadius) {
            allAccommodationsInRadius.add(actualLocation.getAccommodation());
        }
        return allAccommodationsInRadius;
    }
}