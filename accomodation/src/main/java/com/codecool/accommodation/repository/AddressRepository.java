package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findAddressByAccommodation_Id(Long id);
}
