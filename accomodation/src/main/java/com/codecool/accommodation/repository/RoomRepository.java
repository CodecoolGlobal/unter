package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Set<Room> findRoomByAccommodation_Id(Long id);
}
