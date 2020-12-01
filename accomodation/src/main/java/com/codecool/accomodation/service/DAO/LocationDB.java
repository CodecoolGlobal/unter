package com.codecool.accomodation.service.DAO;

import com.codecool.accomodation.model.Coordinates;
import com.codecool.accomodation.model.entity.Location;
import com.codecool.accomodation.repository.LocationRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class LocationDB implements LocationDAO {
    private final LocationRepository repository;

    @Override
    public List<Location> getLocationInDistance(Coordinates coordinates, Double searchRadius) {
        return repository.getAllByLongitudeBetweenAndLatitudeBetween(
                coordinates.getLongitude() - searchRadius,
                coordinates.getLongitude() + searchRadius,
                coordinates.getLatitude() - searchRadius,
                coordinates.getLatitude() + searchRadius
        );
    }
}
