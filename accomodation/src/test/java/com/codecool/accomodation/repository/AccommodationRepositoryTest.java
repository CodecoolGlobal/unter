package com.codecool.accomodation.repository;

import com.codecool.accomodation.model.entity.*;
import com.codecool.accomodation.model.entity.types.RoomType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AccommodationRepositoryTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void smokeTest() {
        assertThat(accommodationRepository).isNotNull();
        assertThat(locationRepository).isNotNull();
    }

    @Test
    public void test_saveNewAccommodation_hasSizeOne() {

        List<Accommodation> originalData = accommodationRepository.findAll();
        Integer originalDataSize = originalData.size();

        Address address = Address.builder()
            .city("Kazincbarcika")
            .street("Utca")
            .zipCode("4444")
            .houseNumber(12)
            .build();

        Coordinate coordinate = Coordinate.builder()
            .latitude(22.00)
            .longitude(32.00)
            .build();

        Location location = Location.builder()
            .address(address)
            .description("Nice!")
            .coordinate(coordinate)
            .build();

        Accommodation accommodation = Accommodation.builder()
            .description("Nice!444négy")
            .location(location)
            .maxNumberOfGuests(4000)
            .name("Házikó")
            .build();

        accommodationRepository.save(accommodation);

        List<Accommodation> accommodations = accommodationRepository.findAll();

        assertThat(accommodations).hasSize(originalDataSize + 1);
    }

    @Test
    public void test_saveSeveralAccommodations_persistAll() {
        List<Accommodation> originalData = accommodationRepository.findAll();
        Integer originalDataSize = originalData.size();

        Address address = Address.builder()
            .city("Budapest")
            .street("Érc utca")
            .houseNumber(3)
            .zipCode("1032")
            .build();

        Location location = Location.builder()
            .coordinate(Coordinate.builder()
                .latitude(22.22)
                .longitude(34.34)
                .build())
            .address(address)
            .build();

        Accommodation accommodation1 = Accommodation.builder()
            .name("One")
            .location(location)
            .maxNumberOfGuests(10)
            .build();

        Accommodation accommodation2 = Accommodation.builder()
            .name("Two")
            .location(location)
            .maxNumberOfGuests(12)
            .build();

        List<Accommodation> newAccommodations = Arrays.asList(accommodation1, accommodation2);

        accommodationRepository.saveAll(newAccommodations);
        List<Accommodation> accommodations = accommodationRepository.findAll();

        assertThat(accommodations).hasSize(originalDataSize + newAccommodations.size());
    }

    @Test
    public void test_accommodationNameShouldBeNotNull_ThrowsException() {
        Accommodation accommodation = Accommodation.builder()
            .maxNumberOfGuests(4)
            .build();

        assertThrows(DataIntegrityViolationException.class,
            () -> accommodationRepository.save(accommodation));
    }

    @Test
    public void test_accommodationNumberOfGuestsShouldBeNotNull_ThrowsException() {
        Accommodation accommodation = Accommodation.builder()
            .name("Your dream holiday house")
            .build();

        assertThrows(DataIntegrityViolationException.class,
            () -> accommodationRepository.save(accommodation));
    }

    @Test
    public void test_accommodationLocationShouldBeNotNull_ThrowsException() {
        Accommodation accommodation = Accommodation.builder()
            .name("Your dream holiday house")
            .build();

        assertThrows(DataIntegrityViolationException.class,
            () -> accommodationRepository.save(accommodation));
    }

    @Test
    public void test_locationIsPersistedWithAccommodation_hasSizeOne() {
        List<Location> originalData = locationRepository.findAll();
        Integer originalDataSize = originalData.size();

        Address address = Address.builder()
            .city("Budapest")
            .street("Érc utca")
            .houseNumber(3)
            .zipCode("1032")
            .build();

        Location location = Location.builder()
            .coordinate(Coordinate.builder()
                .latitude(22.22)
                .longitude(23.23)
                .build())
            .address(address)
            .build();

        Accommodation accommodation = Accommodation.builder()
            .name("accommodation test")
            .maxNumberOfGuests(4)
            .location(location)
            .build();

        location.setAccommodation(accommodation);
        accommodationRepository.save(accommodation);

        List<Location> locations = locationRepository.findAll();
        assertThat(locations)
            .hasSize(originalDataSize + 1)
            .allMatch(location1 -> location.getId() > 0L);
    }

    @Test
    public void test_roomIsPersistedWithAccommodation_HasSizeIncreasesWithOne() {
        List<Room> originalData = roomRepository.findAll();
        Integer originalDataSize = originalData.size();

        Room room = Room.builder()
            .type(RoomType.BEDROOM)
            .build();

        Address address = Address.builder()
            .city("Budapest")
            .street("Érc utca")
            .houseNumber(3)
            .zipCode("1032")
            .build();

        Location location = Location.builder()
            .coordinate(Coordinate.builder()
                .latitude(22.22)
                .longitude(23.23)
                .build())
            .address(address)
            .build();

        Accommodation accommodation = Accommodation.builder()
            .name("accommodation test")
            .maxNumberOfGuests(4)
            .room(room)
            .location(location)
            .build();

        accommodationRepository.save(accommodation);
        List<Room> rooms = roomRepository.findAll();
        assertThat(rooms).hasSize(originalDataSize + 1);
    }
}