package com.codecool.earthbnb.gateway.repository;


import com.codecool.earthbnb.gateway.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findDistinctById(Long userId);

    User findDistinctByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
