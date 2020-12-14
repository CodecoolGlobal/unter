package com.codecool.accommodation;

import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.model.entity.Address;
import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.model.entity.Room;
import com.codecool.accommodation.model.entity.types.BedType;
import com.codecool.accommodation.model.entity.types.RoomType;
import com.codecool.accommodation.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

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
                    .city("Dummy City")
                    .street("Dummy Street")
                    .zipCode("dum-dum")
                    .houseNumber(12)
                    .build();

            Coordinate coordinate = Coordinate.builder()
                    .latitude(22.00)
                    .longitude(32.00)
                    .build();

            Map<BedType, Integer> beds = new HashMap<>();
            beds.put(BedType.KING, 1);

            Room room = Room.builder()
                .type(RoomType.BEDROOM)
                .beds(beds)
                .build();

            Accommodation accommodation = Accommodation.builder()
                    .hostId(1L)
                    .description("Dummy description")
                    .maxNumberOfGuests(6)
                    .coordinate(coordinate)
                    .name("Dummy Accommodation")
                    .room(room)
                    .address(address)
                    .build();
//            room.setAccommodation(accommodation);
            repository.save(accommodation);
        };
    }
}
