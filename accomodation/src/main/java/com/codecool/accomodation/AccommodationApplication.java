package com.codecool.accomodation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AccommodationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccommodationApplication.class, args);
    }

}
