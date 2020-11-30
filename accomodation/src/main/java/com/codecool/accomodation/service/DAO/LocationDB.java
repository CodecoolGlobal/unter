package com.codecool.accomodation.service.DAO;

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
    public List<Location> getLocationInDistance(Double latitude, Double longitude, Double radius) {
        return repository.getAllByLongitudeBetweenAndLatitudeBetween(
                longitude - radius,
                longitude + radius,
                latitude - radius,
                latitude + radius
        );
    }
}
