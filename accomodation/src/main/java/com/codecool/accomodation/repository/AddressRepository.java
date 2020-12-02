package com.codecool.accomodation.repository;

import com.codecool.accomodation.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
