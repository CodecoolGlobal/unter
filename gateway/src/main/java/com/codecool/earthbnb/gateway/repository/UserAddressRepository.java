package com.codecool.earthbnb.gateway.repository;

import com.codecool.earthbnb.gateway.model.entity.UserAddress;
import com.codecool.earthbnb.gateway.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    boolean existsByUserEntity(UserEntity userEntity);

    UserAddress findByUserEntity(UserEntity userEntity);

    UserAddress findDistinctById(Long addressId);
}
