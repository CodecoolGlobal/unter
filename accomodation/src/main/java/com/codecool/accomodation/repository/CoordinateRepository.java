package com.codecool.accomodation.repository;

import com.codecool.accomodation.model.entity.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
    List<Coordinate> getAllByLongitudeBetweenAndLatitudeBetween(Double startLongitude,
                                                              Double endLongitude,
                                                              Double startLatitude,
                                                              Double endLatitude);
}
