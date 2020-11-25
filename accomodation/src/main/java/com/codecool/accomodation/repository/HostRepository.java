package com.codecool.accomodation.repository;

import com.codecool.accomodation.model.entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, Long> {
}
