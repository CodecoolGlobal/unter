package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findRoomByAccommodation_Id(Long id);
    boolean existsRoomsByAccommodation_Id(Long id);
}
