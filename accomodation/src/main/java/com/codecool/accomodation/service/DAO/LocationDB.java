package com.codecool.accomodation.service.DAO;

import com.codecool.accomodation.model.entity.Coordinate;
import com.codecool.accomodation.model.entity.Location;
import com.codecool.accomodation.repository.LocationRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@RequiredArgsConstructor
public class LocationDB implements LocationDAO {
    private final LocationRepository repository;

    @Override
    public Location getLocationByCoordinate(Coordinate coordinate) {
        return repository.getByCoordinate(coordinate);
    }
}
