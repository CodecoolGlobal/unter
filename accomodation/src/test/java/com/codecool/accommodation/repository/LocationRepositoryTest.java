package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.Address;
import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.model.entity.Location;
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
public class LocationRepositoryTest {

    @Autowired
    private LocationRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CoordinateRepository coordinateRepository;

    @Test
    public void smokeTest() {
        assertThat(repository).isNotNull();
    }

    @Test
    public void test_saveNewLocation_hasSizeIncreasesWithOne() {
        List<Location> originalData = repository.findAll();
        Integer originalDataSize = originalData.size();

        Address address = Address.builder()
            .city("Budapest")
            .street("Érc utca")
            .houseNumber(3)
            .zipCode("1032")
            .build();

        Location location = Location.builder()
                .coordinate(Coordinate.builder()
                    .longitude(24.24)
                    .latitude(42.42)
                    .build())
                .address(address)
                .build();

        repository.saveAndFlush(location);
        List<Location> locations = repository.findAll();
        assertThat(locations).hasSize(originalDataSize + 1);
    }

    @Test
    public void test_saveSeveralLocations_persistAll() {
        List<Location> originalData = repository.findAll();
        Integer originalDataSize = originalData.size();

        Address address = Address.builder()
            .city("Budapest")
            .street("Érc utca")
            .houseNumber(3)
            .zipCode("1032")
            .build();

        Address address2 = Address.builder()
            .city("Budapest")
            .street("Viador utca")
            .houseNumber(3)
            .zipCode("1034")
            .build();

        Location location1 = Location.builder()
                .coordinate(Coordinate.builder()
                    .latitude(23.23)
                    .longitude(42.42)
                    .build())
                .address(address)
                .build();

        Location location2 = Location.builder()
                .coordinate(Coordinate.builder()
                    .longitude(11.2)
                    .latitude(45.45)
                    .build())
                .address(address2)
                .build();

        List<Location> newLocations = Arrays.asList(location1, location2);

        repository.saveAll(newLocations);
        List<Location> locations = repository.findAll();
        assertThat(locations).hasSize(originalDataSize + newLocations.size());
    }

    @Test
    public void test_locationCoordinateShouldBeNotNull_ThrowsException() {
        Address address = Address.builder()
            .city("Budapest")
            .street("Érc utca")
            .houseNumber(3)
            .zipCode("1032")
            .build();

        Location location = Location.builder()
                .address(address)
                .build();

        assertThrows(DataIntegrityViolationException.class, () ->
                repository.saveAndFlush(location));
    }

    @Test
    public void test_locationAddressShouldBeNotNull_ThrowsException() {
        Location location1 = Location.builder()
            .coordinate(Coordinate.builder()
                .latitude(23.23)
                .longitude(42.42)
                .build())
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            repository.saveAndFlush(location1));
    }

    @Test
    public void test_addressPersistedWithLocation_hasSizeIncreasesWithOne() {
        List<Address> originalData = addressRepository.findAll();
        Integer originalDataSize = originalData.size();

        Address address = Address.builder()
            .city("Budapest")
            .street("Érc utca")
            .houseNumber(3)
            .zipCode("1032")
            .build();

        Location location = Location.builder()
            .address(address)
            .coordinate(Coordinate.builder()
                .longitude(23.23)
                .latitude(45.45)
                .build())
            .build();

        repository.save(location);
        List<Address> addresses = addressRepository.findAll();
        assertThat(addresses).hasSize(originalDataSize + 1);
    }

    @Test
    public void test_coordinatePersistedWithLocation_hasSizeIncreasesWithOne() {
        List<Coordinate> originalData = coordinateRepository.findAll();
        Integer originalDataSize = originalData.size();

        Address address = Address.builder()
            .city("Budapest")
            .street("Érc utca")
            .houseNumber(3)
            .zipCode("1032")
            .build();

        Location location = Location.builder()
            .address(address)
            .coordinate(Coordinate.builder()
                .longitude(23.23)
                .latitude(45.45)
                .build())
            .build();

        repository.save(location);
        List<Coordinate> coordinates = coordinateRepository.findAll();
        assertThat(coordinates).hasSize(originalDataSize + 1);
    }
}