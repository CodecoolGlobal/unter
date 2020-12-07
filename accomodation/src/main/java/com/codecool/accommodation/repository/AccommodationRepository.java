package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    List<Accommodation> findAccommodationsByHostId(Long hostId);
    void deleteAccommodationById(Long accommodationId);
}
