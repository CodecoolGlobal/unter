package com.codecool.accomodation.repository;

import com.codecool.accomodation.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> getAllByLongitudeBetweenAndLatitudeBetween(Double startLongitude,
                                                              Double endLongitude,
                                                              Double startLatitude,
                                                              Double endLatitude);
}
