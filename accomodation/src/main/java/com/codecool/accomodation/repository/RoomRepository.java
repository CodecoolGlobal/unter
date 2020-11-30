package com.codecool.accomodation.repository;

import com.codecool.accomodation.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
