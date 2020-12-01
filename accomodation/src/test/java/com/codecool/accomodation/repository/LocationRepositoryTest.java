package com.codecool.accomodation.repository;

import com.codecool.accomodation.model.entity.Coordinate;
import com.codecool.accomodation.model.entity.Location;
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

    @Test
    public void smokeTest() {
        assertThat(repository).isNotNull();
    }

    @Test
    public void test_saveNewLocation_hasSizeOne() {
        List<Location> originalData = repository.findAll();
        Integer originalDataSize = originalData.size();

        Location location = Location.builder()
                .coordinate(Coordinate.builder()
                    .longitude(24.24)
                    .latitude(42.42)
                    .build())
                .build();

        repository.saveAndFlush(location);
        List<Location> locations = repository.findAll();
        assertThat(locations).hasSize(originalDataSize + 1);
    }

    @Test
    public void test_saveSeveralLocations_persistAll() {
        List<Location> originalData = repository.findAll();
        Integer originalDataSize = originalData.size();

        Location location1 = Location.builder()
                .coordinate(Coordinate.builder()
                    .latitude(23.23)
                    .longitude(42.42)
                    .build())
                .build();

        Location location2 = Location.builder()
                .coordinate(Coordinate.builder()
                    .longitude(11.2)
                    .latitude(45.45)
                    .build())
                .build();

        List<Location> newLocations = Arrays.asList(location1, location2);

        repository.saveAll(newLocations);
        List<Location> locations = repository.findAll();
        assertThat(locations).hasSize(originalDataSize + newLocations.size());
    }

    @Test
    public void test_locationCoordinateShouldBeNotNull_ThrowsException() {
        Location location = Location.builder()
                .build();

        assertThrows(DataIntegrityViolationException.class, () ->
                repository.saveAndFlush(location));
    }
}