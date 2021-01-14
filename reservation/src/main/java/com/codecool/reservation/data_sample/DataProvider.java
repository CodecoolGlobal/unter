package com.codecool.reservation.data_sample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

@Service
@ActiveProfiles("production")
public class DataProvider implements CommandLineRunner {
    private final ReservationCreator creator;

    public DataProvider(ReservationCreator creator) {
        this.creator = creator;
    }

    @Override
    public void run(String... args) {
        creator.initialize();
    }
}
