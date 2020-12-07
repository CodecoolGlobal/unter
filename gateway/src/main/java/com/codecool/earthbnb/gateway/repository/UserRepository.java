package com.codecool.earthbnb.gateway.repository;


import com.codecool.earthbnb.gateway.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findDistinctById(Long userId);

    UserEntity findDistinctByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);
}
