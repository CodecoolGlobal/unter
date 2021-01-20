package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    List<Accommodation> findAccommodationsByHostId(Long hostId);
   Accommodation findAccommodationById(Long accommodationId);
   Accommodation findDistinctById(Long accommodationId);
    void deleteAccommodationById(Long accommodationId);


    Optional<Accommodation> findById(Long id);
}
