package com.codecool.accomodation.repository;

import com.codecool.accomodation.model.entity.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class LocationRepositoryTest {

    @Autowired
    private LocationRepository repository;

    @Test
    public void test_saveNewLocation_hasSizeOne() {
        Location location = Location.builder()
            .latitude(23.23)
            .longitude(42.42)
            .build();

        repository.saveAndFlush(location);
        List<Location> locations = repository.findAll();
        assertThat(locations).hasSize(1);
    }

    @Test
    public void test_locationLongitudeShouldBeNotNull_ThrowsException() {
        Location location = Location.builder()
            .latitude(23.23)
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            repository.saveAndFlush(location));
    }

    @Test
    public void test_locationLatitudeShouldBeNotNull_ThrowsException() {
        Location location = Location.builder()
            .longitude(23.23)
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            repository.saveAndFlush(location));
    }
}