package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
    List<Coordinate> getAllByLongitudeBetweenAndLatitudeBetween(Double startLongitude,
                                                              Double endLongitude,
                                                              Double startLatitude,
                                                              Double endLatitude);

    Coordinate findCoordinateByAccommodation_Id(Long id);
    boolean existsCoordinateByAccommodation_Id(Long id);
}