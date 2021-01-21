package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.model.entity.Address;
import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.model.entity.Room;
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
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccommodationRepository accommodationRepository;

    private Address testAddress;

    @BeforeEach
    public void setUp() {
        testAddress = Address.builder()
            .city("Test City")
            .street("Test street")
            .houseNumber("3")
            .zipCode("test zip code")
            .country("test")
            .build();
    }

    @Test
    public void smokeTest() {
        assertThat(addressRepository).isNotNull();
    }

    @Test
    public void test_emptyDb_isEmpty() {
        List<Address> accommodations = addressRepository.findAll();
        assertThat(accommodations).isEmpty();
    }

    @Test
    public void test_saveNewAddress_hasSizeOne() {
        addressRepository.save(testAddress);
        List<Address> addresses = addressRepository.findAll();
        Address found = addresses.get(0);

        assertThat(addresses).hasSize(1);
        assertThat(addresses).contains(testAddress);
        assertThat(found).isNotNull();
        assertThat(found).isEqualTo(testAddress);
        assertEquals(found.getCity(), testAddress.getCity());
    }

    @Test
    public void test_saveSeveralAddresses_persistAll() {
        // build another address
        Address testAddress2 = Address.builder()
            .country("Test")
            .city("Test City2")
            .street("Test street2")
            .houseNumber("3")
            .zipCode("test zip code2")
            .build();

        // save accommodations
        List<Address> newAddresses = Arrays.asList(testAddress, testAddress2);
        addressRepository.saveAll(newAddresses);

        // test
        List<Address> addresses = addressRepository.findAll();
        assertThat(addresses).hasSize(newAddresses.size());
        assertThat(addresses).contains(testAddress, testAddress2);
    }

    @Test
    public void test_findAddressById_shouldBeFound() {
        Long id = addressRepository.saveAndFlush(testAddress).getId();
        Address found = addressRepository.findById(id).get();

        assertThat(found).isNotNull();
        assertThat(found).isEqualTo(testAddress);
        assertEquals(found.getCity(), testAddress.getCity());
        assertEquals(found.getStreet(), testAddress.getStreet());
    }

    @Test
    public void findAddressByAccommodationId_shouldBeFound() {
        // build full accommodation with Address
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

        Accommodation testAccommodation = Accommodation.builder()
            .hostId(1L)
            .rooms(rooms)
            .description("Test")
            .coordinate(coordinate)
            .address(address)
            .maxNumberOfGuests(4000)
            .name("Test")
            .build();

        // save accommodation, get accommodation id
        accommodationRepository.save(testAccommodation);
        Long accId = accommodationRepository.findAll().get(0).getId();

        // test
        Address found = addressRepository.findAddressByAccommodation_Id(accId);
        assertThat(found).isNotNull();
        assertThat(found).isEqualTo(address);
        assertEquals(found.getCity(), address.getCity());
    }

    @Test
    public void findAddressByNonExistingAccommodationId_throwsException() {

        assertFalse(addressRepository.existsAddressByAccommodation_Id(5L));
    }

    @Test
    public void test_addressCountryFieldShouldBeNotNull_throwsException() {
        // build address
        Address address = Address.builder()
            // missing country field
            .city("Test City2")
            .street("Test street2")
            .houseNumber("3")
            .zipCode("test zip code2")
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            addressRepository.saveAndFlush(address));
    }

    @Test
    public void test_addressHouseNumberShouldBeNotNull_throwsException() {
        // build address
        Address address = Address.builder()
            .country("Test")
            .city("Test City")
            .street("Test street")
            // missing house number field
            .zipCode("test zip code")
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            addressRepository.saveAndFlush(address));
    }

    @Test
    public void test_addressCityShouldBeNotNull_throwsException() {
        // build address
        Address address = Address.builder()
            .country("Test")
            // missing city field
            .street("Test street")
            .houseNumber("3")
            .zipCode("test zip code")
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            addressRepository.saveAndFlush(address));
    }

    @Test
    public void test_addressStreetShouldBeNotNull_throwsException() {
        // build address
        Address address = Address.builder()
            .country("Test")
            .city("Test City")
            // missing street field
            .houseNumber("3")
            .zipCode("test zip code")
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            addressRepository.saveAndFlush(address));
    }

    @Test
    public void test_addressZipCodeShouldBeNotNull_throwsException() {
        // build address
        Address address = Address.builder()
            .country("Test")
            .city("Test City")
            .street("Test street")
            .houseNumber("3")
            // missing zipCode field
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            addressRepository.saveAndFlush(address));
    }

    @Test
    public void test_updateAddress_saveUpdatedFields() {
        // save accommodation
        addressRepository.saveAndFlush(testAddress);
        Address addressToEdit = addressRepository.findAll().get(0);

        // update fields
        addressToEdit.setCity("updated city");
        addressToEdit.setStreet("updated street");

        // save updated address
        addressRepository.save(addressToEdit);
        Address updated = addressRepository.findById(addressToEdit.getId()).get();

        // test
        assertEquals(updated.getId(), addressToEdit.getId());
        assertEquals(updated.getCity(), "updated city");
        assertEquals(updated.getStreet(), "updated street");
    }

    @Test
    public void test_deleteAddressById_notFound() {
        // save address, and get addresses
        addressRepository.save(testAddress);
        List<Address> addresses = addressRepository.findAll();
        Address found = addresses.get(0);
        int sizeOfAddresses = addresses.size();

        // delete address
        addressRepository.deleteById(found.getId());

        // test
        List<Address> changedAddressList = addressRepository.findAll();
        assertThat(changedAddressList).hasSize(sizeOfAddresses - 1);
        assertThat(changedAddressList).doesNotContain(found);
    }

    @Test
    public void test_deleteAllAddresses_shouldBeEmpty() {
        // build another address
        Address testAddress2 = Address.builder()
            .country("Test")
            .city("Test City2")
            .street("Test street2")
            .houseNumber("3")
            .zipCode("test zip code2")
            .build();

        // save accommodations
        addressRepository.saveAll(Arrays.asList(testAddress, testAddress2));

        // delete all
        addressRepository.deleteAll();

        // test
        List<Address> addresses = addressRepository.findAll();
        assertThat(addresses).isEmpty();
        assertThat(addresses).hasSize(0);
        assertThat(addresses).doesNotContain(testAddress, testAddress2);
    }
}