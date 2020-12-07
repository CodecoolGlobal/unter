package com.codecool.accommodation.service.DAO;

import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.model.entity.Location;
import com.codecool.accommodation.repository.LocationRepository;
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
