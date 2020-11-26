package com.codecool.accomodation.repository;

import com.codecool.accomodation.model.entity.Accommodation;
import com.codecool.accomodation.model.entity.Address;
import com.codecool.accomodation.model.entity.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AccommodationRepositoryTest {

    @Autowired
    private AccommodationRepository repository;

    @Test
    public void test_saveNewAccommodation_hasSizeOne() {

        Address address = Address.builder()
            .city("Kazincbarcika")
            .street("Utca")
            .zipCode("4444")
            .houseNumber(12)
            .build();

        Location location = Location.builder()
            .address(address)
            .description("Nice!")
            .latitude(22.00)
            .longitude(32.00)
            .build();

        Accommodation accommodation = Accommodation.builder()
            .description("Nice!444négy")
            .location(location)
            .maxNumberOfGuests(4000)
            .name("Házikó")
            .build();

        repository.save(accommodation);

        List<Accommodation> accommodations = repository.findAll();

        assertThat(accommodations).hasSize(1);
    }

    @Test
    public void test_accommodationNameShouldBeNotNull_ThrowsException() {
        Accommodation accommodation = Accommodation.builder()
            .maxNumberOfGuests(4)
            .build();

        assertThrows(DataIntegrityViolationException.class,
            () -> repository.save(accommodation));
    }

    @Test
    public void test_accommodationNumberOfGuestsShouldBeNotNull_ThrowsException() {
        Accommodation accommodation = Accommodation.builder()
            .name("Your dream holiday house")
            .build();

        assertThrows(DataIntegrityViolationException.class,
            () -> repository.save(accommodation));
    }
}