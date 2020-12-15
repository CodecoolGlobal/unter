package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.*;
import com.codecool.accommodation.model.entity.types.BedType;
import com.codecool.accommodation.model.entity.types.RoomType;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
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

    private Accommodation testAccommodation;

    private Integer originalDataSize;

    @BeforeEach
    public void setUp() {
        List<Accommodation> originalData = accommodationRepository.findAll();
        this.originalDataSize = originalData.size();

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

        Set<Room> rooms = new HashSet<>();
        rooms.add(room);

        testAccommodation = Accommodation.builder()
            .hostId(1L)
            .rooms(rooms)
            .description("Test")
            .coordinate(coordinate)
            .address(address)
            .maxNumberOfGuests(4000)
            .name("Test")
            .build();
    }

    @Test
    public void smokeTest() {
        assertThat(accommodationRepository).isNotNull();
        assertThat(coordinateRepository).isNotNull();
        assertThat(roomRepository).isNotNull();
        assertThat(addressRepository).isNotNull();
    }

    @Test
    public void test_emptyDb_isEmpty() {
        List<Accommodation> accommodations = accommodationRepository.findAll();
        assertThat(accommodations).isEmpty();
    }

    @Test
    public void test_saveNewAccommodation_hasSizeOne() {
        accommodationRepository.saveAndFlush(testAccommodation);

        // test if db size increased with one and contains the saved accommodation
        List<Accommodation> accommodations = accommodationRepository.findAll();
        assertThat(accommodations).hasSize(originalDataSize + 1);
        assertThat(accommodations).contains(testAccommodation);

        // test if found accommodation's name is the same as the the one's that was saved
        Accommodation acc = accommodations.get(0);
        assertThat(acc).isNotNull();
        assertThat(acc.getCoordinate()).isEqualTo(testAccommodation.getCoordinate());
        assertEquals(acc.getName(), testAccommodation.getName());
        assertEquals(acc.getDescription(), testAccommodation.getDescription());
    }

    @Test
    public void test_findAccommodationById_shouldFind() {
        Long id = accommodationRepository.saveAndFlush(testAccommodation).getId();
        Accommodation found = accommodationRepository.findById(id).get();

        assertEquals(found.getName(), testAccommodation.getName());
        assertEquals(found.getDescription(), testAccommodation.getDescription());

        assertThat(found.getCoordinate()).isEqualTo(testAccommodation.getCoordinate());
        assertThat(found).isEqualTo(testAccommodation);
    }

    @Test
    public void test_saveSeveralAccommodations_persistAll() {
        // build another accommodation
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

        Set<Room> rooms2 =new HashSet<>();
        rooms2.add(room2);

        Accommodation accommodation2 = Accommodation.builder()
            .hostId(1L)
            .rooms(rooms2)
            .description("Test")
            .coordinate(coordinate2)
            .address(address2)
            .maxNumberOfGuests(5)
            .name("Test")
            .build();

        // save accommodations and get all from db
        List<Accommodation> newAccommodations = Arrays.asList(testAccommodation, accommodation2);
        accommodationRepository.saveAll(newAccommodations);
        List<Accommodation> accommodations = accommodationRepository.findAll();

        // test
        assertThat(accommodations).hasSize(originalDataSize + newAccommodations.size());
        assertThat(accommodations).contains(testAccommodation, accommodation2);
    }

    @Test
    public void test_accommodationNameShouldBeNotNull_throwsException() {
        // build accommodation
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

        Accommodation accommodation = Accommodation.builder()
            .hostId(1L)
            .rooms(null)
            .description("Test")
            .coordinate(coordinate)
            .address(address)
            .maxNumberOfGuests(4000)
            // missing name field
            .build();

        // test
        assertThrows(DataIntegrityViolationException.class,
            () -> accommodationRepository.saveAndFlush(accommodation));
    }

    @Test
    public void test_accommodationHostIdShouldBeNotNull_throwsException() {
        // build accommodation
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

        Accommodation accommodation = Accommodation.builder()
            // missing hostId field
            .rooms(null)
            .description("Test")
            .coordinate(coordinate)
            .address(address)
            .maxNumberOfGuests(4000)
            .name("Test")
            .build();

        // test
        assertThrows(DataIntegrityViolationException.class,
            () -> accommodationRepository.saveAndFlush(accommodation));
    }

    @Test
    public void test_accommodationMaxNumberOfGuestsShouldNotBeNull_throwsException() {
        // build accommodation
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

        Accommodation accommodation = Accommodation.builder()
            .hostId(1L)
            .rooms(null)
            .description("Test")
            .coordinate(coordinate)
            .address(address)
            // missing maxNumberOfGuest field
            .name("Test")
            .build();

        // test
        assertThrows(DataIntegrityViolationException.class,
            () -> accommodationRepository.saveAndFlush(accommodation));
    }

    @Test
    public void test_accommodationCoordinateShouldBeNotNull_throwsException() {
        // build accommodation
        Address address = Address.builder()
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber(12)
            .build();

        Accommodation accommodation = Accommodation.builder()
            .hostId(1L)
            .rooms(null)
            .description("Test")
            // missing coordinate field
            .address(address)
            .maxNumberOfGuests(4)
            .name("Test")
            .build();

        // test
        assertThrows(DataIntegrityViolationException.class,
            () -> accommodationRepository.saveAndFlush(accommodation));
    }

    @Test
    public void test_accommodationAddressShouldBeNotNull_throwsException() {
        // build accommodation
        Coordinate coordinate = Coordinate.builder()
            .latitude(22.00)
            .longitude(32.00)
            .build();

        Accommodation accommodation = Accommodation.builder()
            .hostId(1L)
            .rooms(null)
            .description("Test")
            .coordinate(coordinate)
            // missing address field
            .maxNumberOfGuests(4)
            .name("Test")
            .build();

        // test
        assertThrows(DataIntegrityViolationException.class,
            () -> accommodationRepository.saveAndFlush(accommodation));
    }

    @Test
    public void test_coordinateIsPersistedWithAccommodation_hasSizeIncreasedWithOne() {
        // get original size of coordinate list before saving
        List<Coordinate> originalCoordinates = coordinateRepository.findAll();
        int quantityOfCoordinates = originalCoordinates.size();

        // save accommodation, get updated coordinate list
        accommodationRepository.save(testAccommodation);
        List<Coordinate> updatedCoordinates = coordinateRepository.findAll();

        // test
        assertThat(updatedCoordinates)
            .hasSize(quantityOfCoordinates + 1)
            .allMatch(coordinate -> coordinate.getId() > 0L);
        assertThat(updatedCoordinates).contains(testAccommodation.getCoordinate());
    }

    @Test
    public void test_roomIsPersistedWithAccommodation_hasSizeIncreasedWithOne() {
        // get original size of room list before saving
        List<Room> originalRooms = roomRepository.findAll();
        int quantityOfRooms = originalRooms.size();

        // save accommodation, get updated room list
        accommodationRepository.save(testAccommodation);
        List<Room> rooms = roomRepository.findAll();

        // test
        assertThat(rooms)
            .hasSize(quantityOfRooms + 1)
            .allMatch(room1 -> room1.getId() > 0L);
    }

    @Test
    public void test_addressIsPersistedWithAccommodation_hasSizeIncreasedWithOne() {
        // get original size of address list before saving
        List<Address> originalAddresses = addressRepository.findAll();
        int quantityOfAddresses = originalAddresses.size();

        // save accommodation, get updated address list
        accommodationRepository.save(testAccommodation);
        List<Address> addresses = addressRepository.findAll();

        // test
        assertThat(addresses)
            .hasSize(quantityOfAddresses + 1)
            .allMatch(address -> address.getId() > 0L);
        assertThat(addresses).contains(testAccommodation.getAddress());
    }

    @Test
    public void test_accommodationUpdated_fieldsAreChanged() {
        // saved accommodation
        accommodationRepository.saveAndFlush(testAccommodation);
        Accommodation accToEdit = accommodationRepository.findAll().get(0);

        // update fields
        accToEdit.setName("updated name");
        accToEdit.setDescription("updated description");

        // save updated accommodation
        accommodationRepository.save(accToEdit);
        Accommodation updated = accommodationRepository.findById(accToEdit.getId()).get();

        // test
        assertEquals(updated.getId(), accToEdit.getId());
        assertEquals(updated.getName(), "updated name");
        assertEquals(updated.getDescription(), "updated description");
    }

    @Test
    public void test_deleteAccommodationById_notFound() {
        // saved accommodation
        accommodationRepository.saveAndFlush(testAccommodation);
        Accommodation accToDelete = accommodationRepository.findAll().get(0);

        // delete accommodation by id
        accommodationRepository.deleteAccommodationById(accToDelete.getId());

        // test if accommodation list got increased with one
        List<Accommodation> accommodations = accommodationRepository.findAll();
        assertThat(accommodations).hasSize(Math.max(0, originalDataSize - 1));
        assertThat(accommodations).isEmpty();

        // test accommodation was not found by id
        assertThrows(NoSuchElementException.class,
            () -> accommodationRepository.findById(accToDelete.getId()).get());
    }

    @Test
    public void test_coordinatesDeletedWithAccommodation_hasSizeDecreasesWithOne() {
        // saved accommodation
        accommodationRepository.saveAndFlush(testAccommodation);
        Accommodation accToDelete = accommodationRepository.findAll().get(0);

        // get coordinates, size of list
        List<Coordinate> coordinates = coordinateRepository.findAll();
        int sizeOfCoordinates = coordinates.size();

        // delete accommodation by id
        accommodationRepository.deleteAccommodationById(accToDelete.getId());

        // test if coordinate list doesn't contain the coordinates of the deleted accommodation
        List<Coordinate> changedCoordinateList = coordinateRepository.findAll();
        assertThat(changedCoordinateList).hasSize(Math.max(0, sizeOfCoordinates - 1));
        assertThat(changedCoordinateList).doesNotContain(accToDelete.getCoordinate());

        // no coordinates are found by id of deleted accommodation
        Coordinate coordinate = coordinateRepository.findCoordinateByAccommodation_Id(accToDelete.getId());
        assertThat(coordinate).isNull();
    }

    @Test
    public void test_addressDeletedWithAccommodation_hasSizeDecreasesWithOne() {
        // saved accommodation
        accommodationRepository.saveAndFlush(testAccommodation);
        Accommodation accToDelete = accommodationRepository.findAll().get(0);

        // get addresses, size of list
        List<Address> addresses = addressRepository.findAll();
        int sizeOfAddresses = addresses.size();

        // delete accommodation by id
        accommodationRepository.deleteAccommodationById(accToDelete.getId());

        // test if coordinate list doesn't contain the coordinates of the deleted accommodation
        List<Address> changedAddressList = addressRepository.findAll();
        assertThat(changedAddressList).hasSize(Math.max(0, sizeOfAddresses - 1));
        assertThat(changedAddressList).doesNotContain(accToDelete.getAddress());

        // no coordinates are found by id of deleted accommodation
        Address address = addressRepository.findAddressByAccommodation_Id(accToDelete.getId());
        assertThat(address).isNull();
    }

    @Test
    public void test_roomsDeletedWithAccommodation_isNull() {
        // saved accommodation
        accommodationRepository.saveAndFlush(testAccommodation);
        Accommodation accToDelete = accommodationRepository.findAll().get(0);

        // get addresses, size of list
        List<Room> rooms = roomRepository.findAll();
        int sizeOfRooms = rooms.size();

        // delete accommodation by id
        accommodationRepository.deleteAccommodationById(accToDelete.getId());

        // test if room list size is decreased after the deletion
        List<Room> changedRoomList = roomRepository.findAll();
        assertThat(changedRoomList).hasSize(Math.max(0, sizeOfRooms - 1));

        // no rooms are found by id of deleted accommodation
        Set<Room> changedRooms = roomRepository.findRoomByAccommodation_Id(accToDelete.getId());
        assertThat(changedRooms).isEmpty();
    }
}