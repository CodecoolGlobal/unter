package com.codecool.accomodation.repository;

import com.codecool.accomodation.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
