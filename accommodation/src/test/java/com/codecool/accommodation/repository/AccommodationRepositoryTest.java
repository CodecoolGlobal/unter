package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.*;
import com.codecool.accommodation.model.entity.types.BedType;
import com.codecool.accommodation.model.entity.types.RoomType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class AccommodationRepositoryTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CoordinateRepository coordinateRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Integer originalDataSize;

    @BeforeEach
    public void setUp() {
        List<Accommodation> originalData = accommodationRepository.findAll();
        this.originalDataSize = originalData.size();
    }

    @Test
    public void smokeTest() {
        assertThat(accommodationRepository).isNotNull();
        assertThat(coordinateRepository).isNotNull();
        assertThat(roomRepository).isNotNull();
        assertThat(addressRepository).isNotNull();
    }

    @Test
    public void test_saveNewAccommodation_hasSizeOne() {
        Address address = Address.builder()
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber(12)
            .build();

        Coordinate coordinate = Coordinate.builder()
            .latitude(22.00)
            .longitude(32.00)
            .build();

        Map<BedType, Integer> beds = new HashMap<>();
        beds.put(BedType.KING, 1);

        Room room = Room.builder()
            .type(RoomType.BEDROOM)
            .beds(beds)
            .build();

        Accommodation accommodation = Accommodation.builder()
            .hostId(1L)
            .room(room)
            .description("Test")
            .coordinate(coordinate)
            .address(address)
            .maxNumberOfGuests(4000)
            .name("Test")
            .build();

        accommodationRepository.save(accommodation);

        List<Accommodation> accommodations = accommodationRepository.findAll();

        assertThat(accommodations).hasSize(originalDataSize + 1);
    }

    @Test
    public void test_saveSeveralAccommodations_persistAll() {
        // build acc 1
        Address address1 = Address.builder()
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber(12)
            .build();

        Coordinate coordinate1 = Coordinate.builder()
            .latitude(22.00)
            .longitude(32.00)
            .build();

        Map<BedType, Integer> beds1 = new HashMap<>();
        beds1.put(BedType.KING, 1);

        Room room1 = Room.builder()
            .type(RoomType.BEDROOM)
            .beds(beds1)
            .build();

        Accommodation accommodation1 = Accommodation.builder()
            .hostId(1L)
            .room(room1)
            .description("Test")
            .coordinate(coordinate1)
            .address(address1)
            .maxNumberOfGuests(4000)
            .name("Test")
            .build();

        // build acc 2
        Address address2 = Address.builder()
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber(12)
            .build();

        Coordinate coordinate2 = Coordinate.builder()
            .latitude(13.00)
            .longitude(13.00)
            .build();

        Map<BedType, Integer> beds2 = new HashMap<>();
        beds2.put(BedType.KING, 1);

        Room room2 = Room.builder()
            .type(RoomType.BEDROOM)
            .beds(beds2)
            .build();

        Accommodation accommodation2 = Accommodation.builder()
            .hostId(1L)
            .room(room2)
            .description("Test")
            .coordinate(coordinate2)
            .address(address2)
            .maxNumberOfGuests(5)
            .name("Test")
            .build();

        // test
        List<Accommodation> newAccommodations = Arrays.asList(accommodation1, accommodation2);

        accommodationRepository.saveAll(newAccommodations);
        List<Accommodation> accommodations = accommodationRepository.findAll();

        assertThat(accommodations).hasSize(originalDataSize + newAccommodations.size());
    }

    @Test
    public void test_accommodationNameShouldBeNotNull_ThrowsException() {
        //build acc
        Address address = Address.builder()
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber(12)
            .build();

        Coordinate coordinate = Coordinate.builder()
            .latitude(22.00)
            .longitude(32.00)
            .build();

        Map<BedType, Integer> beds = new HashMap<>();
        beds.put(BedType.KING, 1);

        Room room1 = Room.builder()
            .id(1L)
            .type(RoomType.BEDROOM)
            .beds(beds)
            .build();

        Accommodation accommodation = Accommodation.builder()
            .hostId(1L)
            .room(room1)
            .description("Test")
            .coordinate(coordinate)
            .address(address)
            .maxNumberOfGuests(4000)
            // missing name field
            .build();

        assertThrows(DataIntegrityViolationException.class,
            () -> accommodationRepository.save(accommodation));
    }

    @Test
    public void test_accommodationNumberOfGuestsShouldBeNotNull_ThrowsException() {
        // build acc
        Address address = Address.builder()
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber(12)
            .build();

        Coordinate coordinate = Coordinate.builder()
            .latitude(22.00)
            .longitude(32.00)
            .build();

        Map<BedType, Integer> beds = new HashMap<>();
        beds.put(BedType.KING, 1);

        Room room1 = Room.builder()
            .id(1L)
            .type(RoomType.BEDROOM)
            .beds(beds)
            .build();

        Accommodation accommodation = Accommodation.builder()
            .hostId(1L)
            .room(room1)
            .description("Test")
            .coordinate(coordinate)
            .address(address)
            // missing maxNumberOfGuests field
            .name("Test")
            .build();

        assertThrows(DataIntegrityViolationException.class,
            () -> accommodationRepository.save(accommodation));
    }

    @Test
    public void test_accommodationCoordinateShouldBeNotNull_ThrowsException() {
        // build acc
        Address address = Address.builder()
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber(12)
            .build();

        Map<BedType, Integer> beds = new HashMap<>();
        beds.put(BedType.KING, 1);

        Room room = Room.builder()
            .type(RoomType.BEDROOM)
            .beds(beds)
            .build();

        Accommodation accommodation = Accommodation.builder()
            .hostId(1L)
            .room(room)
            .description("Test")
            // missing coordinate field
            .address(address)
            .maxNumberOfGuests(4000)
            .name("Test")
            .build();

        assertThrows(DataIntegrityViolationException.class,
            () -> accommodationRepository.save(accommodation));
    }

    @Test
    public void test_coordinateIsPersistedWithAccommodation_hasSizeOne() {
        // build acc
        Address address = Address.builder()
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber(12)
            .build();

        Coordinate coordinate = Coordinate.builder()
            .latitude(22.00)
            .longitude(32.00)
            .build();

        Map<BedType, Integer> beds = new HashMap<>();
        beds.put(BedType.KING, 1);

        Room room = Room.builder()
            .type(RoomType.BEDROOM)
            .beds(beds)
            .build();

        Accommodation accommodation = Accommodation.builder()
            .hostId(1L)
            .room(room)
            .description("Test1")
            .coordinate(coordinate)
            .address(address)
            .maxNumberOfGuests(4000)
            .name("Test1")
            .build();

        accommodationRepository.save(accommodation);
        List<Coordinate> coordinates = coordinateRepository.findAll();

        assertThat(coordinates)
            .hasSize(originalDataSize + 1)
            .allMatch(coordinate1 -> coordinate1.getId() > 0L);
    }

    @Test
    public void test_roomIsPersistedWithAccommodation_HasSizeIncreasesWithOne() {
        Address address = Address.builder()
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber(12)
            .build();

        Coordinate coordinate = Coordinate.builder()
            .latitude(22.00)
            .longitude(32.00)
            .build();

        Map<BedType, Integer> beds = new HashMap<>();
        beds.put(BedType.KING, 1);

        Room room = Room.builder()
            .type(RoomType.BEDROOM)
            .beds(beds)
            .build();

        Accommodation accommodation = Accommodation.builder()
            .hostId(1L)
            .room(room)
            .description("Test1")
            .coordinate(coordinate)
            .address(address)
            .maxNumberOfGuests(4000)
            .name("Test1")
            .build();

        accommodationRepository.save(accommodation);
        List<Room> rooms = roomRepository.findAll();
        assertThat(rooms)
            .hasSize(originalDataSize + 1)
            .allMatch(room1 -> room1.getId() > 0L);
    }
}