//package com.codecool.accomodation.repository;
//
//import com.codecool.accomodation.model.entity.Location;
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
//public class LocationRepositoryTest {
//
//    @Autowired
//    private LocationRepository repository;
//
//    @Test
//    public void smokeTest() {
//        assertThat(repository).isNotNull();
//    }
//
////    @Test
////    public void test_saveNewLocation_hasSizeOne() {
////        List<Location> originalData = repository.findAll();
////        Integer originalDataSize = originalData.size();
////
////        Location location = Location.builder()
////                .latitude(23.23)
////                .longitude(42.42)
////                .build();
////
////        repository.saveAndFlush(location);
////        List<Location> locations = repository.findAll();
////        assertThat(locations).hasSize(originalDataSize + 1);
////    }
//
//    @Test
//    public void test_saveSeveralLocations_persistAll() {
//        List<Location> originalData = repository.findAll();
//        Integer originalDataSize = originalData.size();
//
//        Location location1 = Location.builder()
//                .latitude(23.23)
//                .longitude(42.42)
//                .build();
//
//        Location location2 = Location.builder()
//                .latitude(23.23)
//                .longitude(42.42)
//                .build();
//
//        List<Location> newLocations = Arrays.asList(location1, location2);
//
//        repository.saveAll(newLocations);
//        List<Location> locations = repository.findAll();
//        assertThat(locations).hasSize(originalDataSize + newLocations.size());
//    }
//
//    @Test
//    public void test_locationLongitudeShouldBeNotNull_ThrowsException() {
//        Location location = Location.builder()
//                .latitude(23.23)
//                .build();
//
//        assertThrows(DataIntegrityViolationException.class, () ->
//                repository.saveAndFlush(location));
//    }
//
//    @Test
//    public void test_locationLatitudeShouldBeNotNull_ThrowsException() {
//        Location location = Location.builder()
//                .longitude(23.23)
//                .build();
//
//        assertThrows(DataIntegrityViolationException.class, () ->
//                repository.saveAndFlush(location));
//    }
//
//    @Test
//    public void test_findAllWithinCoordinatesExistingValue_positiveAndAllValuesOut() {
//        Double startLongitude = 9.0;
//        Double endLongitude = 12.10;
//        Double startLatitude = 9.0;
//        Double endLatitude = 12.10;
//
//        Location location1 = Location.builder()
//                .latitude(10.00)
//                .longitude(10.00)
//                .build();
//
//        Location location2 = Location.builder()
//                .latitude(12.00)
//                .longitude(12.00)
//                .build();
//
//        Location location3 = Location.builder()
//                .latitude(2.23)
//                .longitude(2.42)
//                .build();
//        List<Location> newLocations = Arrays.asList(location1, location2, location3);
//        repository.saveAll(newLocations);
//
//        List<Location> expectedLocations = Arrays.asList(location1, location2);
//
//        List<Location> actualLocations = repository.getAllByLongitudeBetweenAndLatitudeBetween(startLongitude, endLongitude, startLatitude, endLatitude);
//
//        assertThat(actualLocations).hasSize(expectedLocations.size());
//        assertThat(actualLocations).contains(location1);
//        assertThat(actualLocations).contains(location2);
//    }
//
//    @Test
//    public void test_findAllWithinCoordinatesExistingValue_negativeValuesAndValueBetween() {
//        Double startLongitude = 9.0;
//        Double endLongitude = 12.10;
//        Double startLatitude = -9.0;
//        Double endLatitude = 12.10;
//
//        Location location1 = Location.builder()
//                .latitude(10.00)
//                .longitude(10.00)
//                .build();
//
//        Location location2 = Location.builder()
//                .latitude(12.00)
//                .longitude(12.00)
//                .build();
//
//        Location location3 = Location.builder()
//                .latitude(2.23)
//                .longitude(2.42)
//                .build();
//        List<Location> newLocations = Arrays.asList(location1, location2, location3);
//        repository.saveAll(newLocations);
//
//        List<Location> expectedLocations = Arrays.asList(location1, location2);
//
//        List<Location> actualLocations = repository.getAllByLongitudeBetweenAndLatitudeBetween(startLongitude, endLongitude, startLatitude, endLatitude);
//
//        assertThat(actualLocations).hasSize(expectedLocations.size());
//        assertThat(actualLocations).contains(location1);
//        assertThat(actualLocations).contains(location2);
//    }
//
//    @Test
//    public void test_findAllWithinCoordinates_outsideValues() {
//        Double startLongitude = 9.0;
//        Double endLongitude = 12.10;
//        Double startLatitude = -12.10;
//        Double endLatitude = -9.00;
//        int expectedSize = 0;
//
//        Location location1 = Location.builder()
//                .latitude(10.00)
//                .longitude(10.00)
//                .build();
//
//        Location location2 = Location.builder()
//                .latitude(12.00)
//                .longitude(12.00)
//                .build();
//
//        Location location3 = Location.builder()
//                .latitude(2.23)
//                .longitude(2.42)
//                .build();
//        List<Location> newLocations = Arrays.asList(location1, location2, location3);
//        repository.saveAll(newLocations);
//
//        List<Location> actualLocations = repository.getAllByLongitudeBetweenAndLatitudeBetween(startLongitude, endLongitude, startLatitude, endLatitude);
//
//        assertThat(actualLocations).hasSize(expectedSize);
//    }
//}