package com.codecool.accomodation.repository;

import com.codecool.accomodation.entity.Accomodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccomodationRepository extends JpaRepository<Accomodation, Long> {
}
