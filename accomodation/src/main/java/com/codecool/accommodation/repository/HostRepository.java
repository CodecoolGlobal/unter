package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, Long> {
}
