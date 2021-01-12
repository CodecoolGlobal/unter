package com.codecool.accommodation.repository;

import com.codecool.accommodation.model.entity.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ActiveProfiles("test")
public class CoordinateRepositoryTest {

    @Autowired
    private CoordinateRepository coordinateRepository;

    private Coordinate testCoordinate;

    @BeforeEach
    public void setUp() {
        testCoordinate = Coordinate.builder()
            .longitude(23.23)
            .latitude(32.32)
            .build();
    }

    @Test
    public void smokeTest() {
        assertThat(coordinateRepository).isNotNull();
    }

    @Test
    public void test_saveNewCoordinate_hasSizeOne() {
        // save coordinate
        coordinateRepository.save(testCoordinate);
        List<Coordinate> coordinates = coordinateRepository.findAll();
        Coordinate found = coordinates.get(0);

        // test
        assertThat(coordinates).hasSize(1);
        assertThat(coordinates).contains(testCoordinate);
        assertThat(found).isNotNull();
        assertThat(found).isEqualTo(testCoordinate);
        assertEquals(found.getLatitude(), testCoordinate.getLatitude());
        assertEquals(found.getLongitude(), testCoordinate.getLongitude());
    }

    @Test
    public void test_coordinateLongitudeShouldBeNotNull_ThrowsException() {
        // build coordinate
        Coordinate coordinate = Coordinate.builder()
            // missing longitude field
            .latitude(77.77)
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            coordinateRepository.saveAndFlush(coordinate));
    }

    @Test
    public void test_coordinateLatitudeShouldBeNotNull_ThrowsException() {
        // build coordinate
        Coordinate coordinate = Coordinate.builder()
            .longitude(18.18)
            // missing latitude field
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            coordinateRepository.saveAndFlush(coordinate));
    }

    @Test
    public void test_findAllWithinCoordinatesExistingValue_positiveAndAllValuesOut() {
        Double startLongitude = 9.0;
        Double endLongitude = 12.10;
        Double startLatitude = 9.0;
        Double endLatitude = 12.10;

        Coordinate coordinate1 = Coordinate.builder()
                    .latitude(10.00)
                    .longitude(10.00)
                    .build();

        Coordinate coordinate2 = Coordinate.builder()
                    .latitude(12.00)
                    .longitude(12.00)
                    .build();

        Coordinate coordinate3 = Coordinate.builder()
                    .latitude(2.23)
                    .longitude(2.42)
                    .build();

        coordinateRepository.saveAll(Arrays.asList(coordinate1, coordinate2, coordinate3));

        List<Coordinate> coordinates = coordinateRepository.getAllByLongitudeBetweenAndLatitudeBetween(
            startLongitude,
            endLongitude,
            startLatitude,
            endLatitude
        );

        assertThat(coordinates).contains(coordinate1);
        assertThat(coordinates).contains(coordinate2);
        assertThat(coordinates).doesNotContain(coordinate3);
    }

    @Test
    public void test_findAllWithinCoordinatesExistingValue_negativeValuesAndValueBetween() {
        Double startLongitude = 9.0;
        Double endLongitude = 12.10;
        Double startLatitude = -9.0;
        Double endLatitude = 12.10;

        Coordinate coordinate1 = Coordinate.builder()
                .latitude(10.00)
                .longitude(10.00)
                .build();

        Coordinate coordinate2 = Coordinate.builder()
                .latitude(12.00)
                .longitude(12.00)
                .build();

        Coordinate coordinate3 = Coordinate.builder()
                .latitude(2.23)
                .longitude(2.42)
                .build();

        coordinateRepository.saveAll(Arrays.asList(coordinate1, coordinate2, coordinate3));
        List<Coordinate> coordinates = coordinateRepository.getAllByLongitudeBetweenAndLatitudeBetween(
            startLongitude,
            endLongitude,
            startLatitude,
            endLatitude
        );

        assertThat(coordinates).contains(coordinate1);
        assertThat(coordinates).contains(coordinate2);
        assertThat(coordinates).doesNotContain(coordinate3);
    }
}