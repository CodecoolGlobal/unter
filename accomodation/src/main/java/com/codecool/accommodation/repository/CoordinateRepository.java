package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
    List<Coordinate> getAllByLongitudeBetweenAndLatitudeBetween(Double startLongitude,
                                                              Double endLongitude,
                                                              Double startLatitude,
                                                              Double endLatitude);
}