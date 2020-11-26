package com.codecool.accomodation.repository;

import static org.junit.jupiter.api.Assertions.*;

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