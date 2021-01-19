package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.*;
import com.codecool.accommodation.model.entity.types.BedType;
import com.codecool.accommodation.model.entity.types.RoomType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

    @BeforeEach
    public void setUp() {
        Address address = Address.builder()
            .country("Test")
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber("12")
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

        List<Room> rooms = new ArrayList<>();
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
        assertThat(accommodations).hasSize(1);
        assertThat(accommodations).contains(testAccommodation);

        // test if found accommodation's name is the same as the the one's that was saved
        Accommodation acc = accommodations.get(0);
        assertThat(acc).isNotNull();
        assertThat(acc.getCoordinate()).isEqualTo(testAccommodation.getCoordinate());
        assertEquals(acc.getName(), testAccommodation.getName());
        assertEquals(acc.getDescription(), testAccommodation.getDescription());
    }

    @Test
    public void test_findAccommodationById_shouldBeFound() {
        Long id = accommodationRepository.saveAndFlush(testAccommodation).getId();
        Accommodation found = accommodationRepository.findById(id).get();

        assertEquals(found.getName(), testAccommodation.getName());
        assertEquals(found.getDescription(), testAccommodation.getDescription());

        assertThat(found.getCoordinate()).isEqualTo(testAccommodation.getCoordinate());
        assertThat(found).isEqualTo(testAccommodation);
    }

    @Test
    public void test_findAccommodationsByHostId_shouldBeFound() {
        // build 2 more accommodations with the same hostId but different from test
        Long testHostId = 2L;
        Address address2 = Address.builder()
            .country("Test")
            .city("Test City2")
            .street("Test street2")
            .zipCode("test2")
            .houseNumber("12")
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

        List<Room> rooms2 =new ArrayList<>();
        rooms2.add(room2);

        Accommodation testAccommodation2 = Accommodation.builder()
            .hostId(testHostId)
            .rooms(rooms2)
            .description("Test2")
            .coordinate(coordinate2)
            .address(address2)
            .maxNumberOfGuests(5)
            .name("Test2")
            .build();

        Address address3 = Address.builder()
            .country("Test")
            .city("Test City3")
            .street("Test street3")
            .zipCode("test3")
            .houseNumber("12")
            .build();

        Coordinate coordinate3 = Coordinate.builder()
            .latitude(13.00)
            .longitude(13.00)
            .build();

        Map<BedType, Integer> beds3 = new HashMap<>();
        beds2.put(BedType.KING, 1);

        Room room3 = Room.builder()
            .type(RoomType.BEDROOM)
            .beds(beds2)
            .build();

        List<Room> rooms3 =new ArrayList<>();
        rooms3.add(room3);

        Accommodation testAccommodation3 = Accommodation.builder()
            .hostId(testHostId)
            .rooms(rooms3)
            .description("Test3")
            .coordinate(coordinate3)
            .address(address3)
            .maxNumberOfGuests(5)
            .name("Test3")
            .build();

        // save accommodations
        accommodationRepository.saveAll(Arrays.asList(testAccommodation, testAccommodation2, testAccommodation3));

        // find accommodations by testHostId
        List<Accommodation> foundByHostId = accommodationRepository.findAccommodationsByHostId(testHostId);

        // test
        assertThat(foundByHostId).hasSize(2);
        assertThat(foundByHostId).contains(testAccommodation2, testAccommodation3);
        assertThat(foundByHostId).doesNotContain(testAccommodation);
    }

    @Test
    public void test_findAccommodationByNonExistingHostId_shouldNotBeFound() {
        Long testHostId = 5L;

        // save testAccommodation with hostId = 1L
        accommodationRepository.save(testAccommodation);

        List<Accommodation> found = accommodationRepository.findAccommodationsByHostId(testHostId);
        System.out.println(found);

        // test
        List<Accommodation> foundByHostId = accommodationRepository.findAccommodationsByHostId(testHostId);
        assertThat(foundByHostId).hasSize(0);
        assertThat(foundByHostId).isEmpty();
        assertThat(foundByHostId).doesNotContain(testAccommodation);
    }

    @Test
    public void test_saveSeveralAccommodations_persistAll() {
        // build another accommodation
        Address address2 = Address.builder()
            .country("Test")
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber("12")
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

        List<Room> rooms2 =new ArrayList<>();
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
        assertThat(accommodations).hasSize(2);
        assertThat(accommodations).contains(testAccommodation, accommodation2);
    }

    @Test
    public void test_accommodationNameShouldBeNotNull_throwsException() {
        // build accommodation
        Address address = Address.builder()
            .country("Test")
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber("12")
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
            .country("Test")
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber("12")
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
            .country("Test")
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber("12")
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
            .country("Test")
            .city("Test City")
            .street("Test street")
            .zipCode("test")
            .houseNumber("12")
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
        assertThat(accommodations).hasSize(0);
        assertThat(accommodations).isEmpty();

        // test accommodation was not found by id
        assertThrows(NoSuchElementException.class,
            () -> accommodationRepository.findById(accToDelete.getId()).get());
    }

    @Test
    public void test_coordinatesDeletedWithAccommodation_notFound() {
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
        assertThat(changedCoordinateList).hasSize(sizeOfCoordinates - 1);
        assertThat(changedCoordinateList).doesNotContain(accToDelete.getCoordinate());

        // no coordinates are found by id of deleted accommodation
        Coordinate coordinate = coordinateRepository.findCoordinateByAccommodation_Id(accToDelete.getId());
        assertThat(coordinate).isNull();
        assertFalse(coordinateRepository.existsCoordinateByAccommodation_Id(accToDelete.getId()));
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
        assertThat(changedAddressList).hasSize(sizeOfAddresses - 1);
        assertThat(changedAddressList).doesNotContain(accToDelete.getAddress());

        // no coordinates are found by id of deleted accommodation
        Address address = addressRepository.findAddressByAccommodation_Id(accToDelete.getId());
        assertThat(address).isNull();
        assertFalse(addressRepository.existsAddressByAccommodation_Id(accToDelete.getId()));
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
        assertThat(changedRoomList).hasSize(sizeOfRooms - 1);

        // no rooms are found by id of deleted accommodation
        List<Room> roomSet = roomRepository.findRoomByAccommodation_Id(accToDelete.getId());
        assertThat(roomSet).isEmpty();
        assertFalse(roomRepository.existsRoomsByAccommodation_Id(accToDelete.getId()));
    }

    @Test
    public void test_deleteAllAccommodation_shouldBeEmpty() {
        // build another accommodation
        Address address = Address.builder()
            .country("Test country")
            .city("Test City2")
            .street("Test street2")
            .zipCode("test2")
            .houseNumber("12")
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

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        Accommodation testAccommodation2 = Accommodation.builder()
            .hostId(1L)
            .rooms(rooms)
            .description("Test2")
            .coordinate(coordinate)
            .address(address)
            .maxNumberOfGuests(4000)
            .name("Test2")
            .build();

        // save accommodations
        accommodationRepository.saveAll(Arrays.asList(testAccommodation, testAccommodation2));

        // delete all
        accommodationRepository.deleteAll();

        // test
        List<Accommodation> accommodations = accommodationRepository.findAll();
        assertThat(accommodations).isEmpty();
        assertThat(accommodations).doesNotContain(testAccommodation, testAccommodation2);
    }
}