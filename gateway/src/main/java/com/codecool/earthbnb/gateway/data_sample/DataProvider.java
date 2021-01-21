package com.codecool.earthbnb.gateway.data_sample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

@Service
@ActiveProfiles("production")
public class DataProvider implements CommandLineRunner {
    private final UserCreator creator;

    public DataProvider(UserCreator creator) {
        this.creator = creator;
    }

    @Override
    public void run(String... args) {
        creator.initialize();
    }
}
