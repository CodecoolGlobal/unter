package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.Room;
import com.codecool.accommodation.model.entity.types.BedType;
import com.codecool.accommodation.model.entity.types.RoomType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    private Room testRoom;

    @BeforeEach
    public void setUp() {
        Map< BedType, Integer> beds = new HashMap<>();
        beds.put(BedType.KING, 1);

        testRoom = Room.builder()
            .type(RoomType.BEDROOM)
            .beds(beds)
            .build();
    }

    @Test
    public void smokeTest() {
        assertThat(roomRepository).isNotNull();
    }

    @Test
    public void test_saveNewRoom_hasSizeOne() {
        roomRepository.saveAndFlush(testRoom);

        List<Room> rooms = roomRepository.findAll();
        assertThat(rooms).hasSize(1);
        assertThat(rooms).contains(testRoom);
    }

    @Test
    public void test_emptyDb_isEmtpy() {
        assertThat(roomRepository.findAll()).isEmpty();
    }

    @Test
    public void test_findRoomById_shouldBeFound() {
        roomRepository.save(testRoom);
        Long id = roomRepository.findAll().get(0).getId();

        Room found = roomRepository.findById(id).get();
        assertThat(found).isNotNull();
        assertThat(found).isEqualTo(testRoom);
        assertEquals(found.getType(), testRoom.getType());
    }


}