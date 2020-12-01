package com.codecool.accomodation;

import com.codecool.accomodation.model.entity.Accommodation;
import com.codecool.accomodation.model.entity.Address;
import com.codecool.accomodation.model.entity.Location;
import com.codecool.accomodation.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@EnableEurekaClient
@ActiveProfiles("production")
@RequiredArgsConstructor
public class AccommodationApplication {
    private final AccommodationRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(AccommodationApplication.class, args);
    }

    @Bean
    public ModelMapper mapperCreator(){
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
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
        };
    }

}
