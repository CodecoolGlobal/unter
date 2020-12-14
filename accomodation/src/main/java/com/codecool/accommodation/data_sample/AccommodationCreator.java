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
        NewAccommodation newAccommodation = NewAccommodation.builder()
                .city("Kazincbarcika")
                .street("Utca")
                .zipCode("4444")
                .houseNumber(12)
                .latitude(22.00)
                .longitude(32.00)
                .description("Nice!444négy")
                .maxNumberOfGuests(4000)
                .name("Házikó")
                .picture(null)
                .hostId(1L)
                .build();

        Accommodation accommodation = createAccommodation(newAccommodation);
        repository.save(accommodation);
    }


    private Accommodation createAccommodation(NewAccommodation newAccommodation) {
        Address address = Address.builder()
                .city(newAccommodation.getCity())
                .street(newAccommodation.getStreet())
                .zipCode(newAccommodation.getZipCode())
                .houseNumber(newAccommodation.getHouseNumber())
                .build();

        Coordinate coordinate = Coordinate.builder()
                .latitude(newAccommodation.getLatitude())
                .longitude(newAccommodation.getLongitude())
                .build();

        Accommodation accommodation = Accommodation.builder()
                .description(newAccommodation.getDescription())
                .maxNumberOfGuests(newAccommodation.getMaxNumberOfGuests())
                .name(newAccommodation.getName())
                .hostId(newAccommodation.getHostId())
                .address(address)
                .coordinate(coordinate)
                .pictureUrl(newAccommodation.getPicture())
                .build();

        return accommodation;
    }
}
