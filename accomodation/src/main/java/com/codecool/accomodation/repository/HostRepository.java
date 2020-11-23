package com.codecool.accomodation.repository;

import com.codecool.accomodation.entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, Long> {
}
