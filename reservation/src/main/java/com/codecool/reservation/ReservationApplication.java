package com.codecool.reservation;

import com.codecool.reservation.model.entity.Reservation;
import com.codecool.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootApplication
@EnableEurekaClient
@ActiveProfiles("production")
@RequiredArgsConstructor
public class ReservationApplication {

    @Autowired
    private ReservationRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ReservationApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            Reservation reservation = Reservation.builder()
                    .accommodationId(1L)
                    .guestId(1L)
                    .startDate(LocalDate.of(2021, 12,30))
                    .endDate(LocalDate.of(2022, 1, 20))
                    .build();

            repository.save(reservation);
        };
    }
}
