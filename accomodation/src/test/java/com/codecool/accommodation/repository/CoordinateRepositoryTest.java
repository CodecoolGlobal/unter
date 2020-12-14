//package com.codecool.accommodation.repository;
//
//import com.codecool.accommodation.model.entity.Coordinate;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@ActiveProfiles("test")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class CoordinateRepositoryTest {
//
//    @Autowired
//    private CoordinateRepository coordinateRepository;
//
//    @Test
//    public void smokeTest() {
//        assertThat(coordinateRepository).isNotNull();
//    }
//
//    @Test
//    public void test_saveNewCoordinate_hasSizeIncreasesWithOne() {
//        List<Coordinate> originalData = coordinateRepository.findAll();
//        Integer originalDataSize = originalData.size();
//
//        Coordinate coordinate = Coordinate.builder()
//            .longitude(11.2)
//            .latitude(45.45)
//            .build();
//
//        coordinateRepository.save(coordinate);
//        List<Coordinate> coordinates = coordinateRepository.findAll();
//        assertThat(coordinates).hasSize(originalDataSize + 1);
//    }
//
//    @Test
//    public void test_coordinateLongitudeShouldBeNotNull_ThrowsException() {
//        Coordinate coordinate = Coordinate.builder()
//            .latitude(45.45)
//            .build();
//
//        assertThrows(DataIntegrityViolationException.class, () ->
//            coordinateRepository.saveAndFlush(coordinate));
//    }
//
//    @Test
//    public void test_coordinateLatitudeShouldBeNotNull_ThrowsException() {
//        Coordinate coordinate = Coordinate.builder()
//            .longitude(45.45)
//            .build();
//
//        assertThrows(DataIntegrityViolationException.class, () ->
//            coordinateRepository.saveAndFlush(coordinate));
//    }
//
//    @Test
//    public void test_findAllWithinCoordinatesExistingValue_positiveAndAllValuesOut() {
//        Double startLongitude = 9.0;
//        Double endLongitude = 12.10;
//        Double startLatitude = 9.0;
//        Double endLatitude = 12.10;
//
//        Coordinate coordinate1 = Coordinate.builder()
//                    .latitude(10.00)
//                    .longitude(10.00)
//                    .build();
//
//        Coordinate coordinate2 = Coordinate.builder()
//                    .latitude(12.00)
//                    .longitude(12.00)
//                    .build();
//
//        Coordinate coordinate3 = Coordinate.builder()
//                    .latitude(2.23)
//                    .longitude(2.42)
//                    .build();
//
//        coordinateRepository.saveAll(Arrays.asList(coordinate1, coordinate2, coordinate3));
//
//        List<Coordinate> coordinates = coordinateRepository.getAllByLongitudeBetweenAndLatitudeBetween(
//            startLongitude,
//            endLongitude,
//            startLatitude,
//            endLatitude
//        );
//
//        assertThat(coordinates).contains(coordinate1);
//        assertThat(coordinates).contains(coordinate2);
//        assertThat(coordinates).doesNotContain(coordinate3);
//    }
//
//    @Test
//    public void test_findAllWithinCoordinatesExistingValue_negativeValuesAndValueBetween() {
//        Double startLongitude = 9.0;
//        Double endLongitude = 12.10;
//        Double startLatitude = -9.0;
//        Double endLatitude = 12.10;
//
//        Coordinate coordinate1 = Coordinate.builder()
//                .latitude(10.00)
//                .longitude(10.00)
//                .build();
//
//        Coordinate coordinate2 = Coordinate.builder()
//                .latitude(12.00)
//                .longitude(12.00)
//                .build();
//
//        Coordinate coordinate3 = Coordinate.builder()
//                .latitude(2.23)
//                .longitude(2.42)
//                .build();
//
//        coordinateRepository.saveAll(Arrays.asList(coordinate1, coordinate2, coordinate3));
//        List<Coordinate> coordinates = coordinateRepository.getAllByLongitudeBetweenAndLatitudeBetween(
//            startLongitude,
//            endLongitude,
//            startLatitude,
//            endLatitude
//        );
//
//        assertThat(coordinates).contains(coordinate1);
//        assertThat(coordinates).contains(coordinate2);
//        assertThat(coordinates).doesNotContain(coordinate3);
//    }
//}