package com.codecool.accomodation;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@EnableEurekaClient
@ActiveProfiles("production")
public class AccommodationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccommodationApplication.class, args);
    }

    @Bean
    public ModelMapper mapperCreator(){
        return new ModelMapper();
    }
}
