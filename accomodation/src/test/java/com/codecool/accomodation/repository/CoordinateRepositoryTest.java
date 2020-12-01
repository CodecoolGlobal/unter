package com.codecool.accomodation.repository;

import com.codecool.accomodation.model.entity.Coordinate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CoordinateRepositoryTest {

    @Autowired
    CoordinateRepository coordinateRepository;

    @Test
    public void smokeTest() {
        assertThat(coordinateRepository).isNotNull();
    }

    @Test
    public void test_saveNewCoordinate_hasSizeIncreasesWithOne() {
        List<Coordinate> originalData = coordinateRepository.findAll();
        Integer originalDataSize = originalData.size();

        Coordinate coordinate = Coordinate.builder()
            .longitude(11.2)
            .latitude(45.45)
            .build();

        coordinateRepository.save(coordinate);
        List<Coordinate> coordinates = coordinateRepository.findAll();
        assertThat(coordinates).hasSize(originalDataSize + 1);
    }
}