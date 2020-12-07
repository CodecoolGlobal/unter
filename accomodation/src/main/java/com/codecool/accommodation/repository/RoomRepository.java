package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
