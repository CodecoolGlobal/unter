package com.codecool.accommodation.data_sample;

import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.model.entity.Address;
import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.repository.AccommodationRepository;
import org.springframework.stereotype.Component;

@Component
public class AccommodationCreator {
    private final AccommodationRepository repository;

    public AccommodationCreator(AccommodationRepository repository) {
        this.repository = repository;
    }

    public void initialize() {
        Address address = Address.builder()
                .city("Kazincbarcika")
                .street("Utca")
                .zipCode("4444")
                .houseNumber(12)
                .build();

        Coordinate coordinate = Coordinate.builder()
                .latitude(22.00)
                .longitude(32.00)
                .build();

        Accommodation accommodation = Accommodation.builder()
                .description("Nice!444négy")
                .maxNumberOfGuests(4000)
                .name("Házikó")
                .hostId(1L)
                .address(address)
                .coordinate(coordinate)
                .build();

        repository.save(accommodation);
    }
}
