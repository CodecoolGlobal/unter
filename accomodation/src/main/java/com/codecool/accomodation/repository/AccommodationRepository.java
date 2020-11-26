package com.codecool.accomodation.repository;

import com.codecool.accomodation.model.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    List<Accommodation> findAccommodationsByHostId(Long hostId);
    void deleteAccommodationById(Long accommodationId);
}
