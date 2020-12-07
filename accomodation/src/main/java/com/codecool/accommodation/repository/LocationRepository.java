package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location getByCoordinate(Coordinate coordinate);
}
