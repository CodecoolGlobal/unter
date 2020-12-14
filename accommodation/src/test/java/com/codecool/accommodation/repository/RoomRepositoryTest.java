//package com.codecool.accommodation.repository;
//
//import com.codecool.accommodation.model.entity.Room;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@ActiveProfiles("test")
//public class RoomRepositoryTest {
//
//    @Autowired
//    private RoomRepository repository;
//
//    @Test
//    public void test_saveNewRoom_hasSizeOne() {
//        Room room = Room.builder().build();
//        repository.saveAndFlush(room);
//
//        List<Room> rooms = repository.findAll();
//        assertThat(rooms).hasSize(1);
//    }
//}