package com.codecool.earthbnb.gateway;

import com.codecool.earthbnb.gateway.model.entity.User;
import com.codecool.earthbnb.gateway.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@RequiredArgsConstructor
public class GatewayApplication{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            User userEntity = User.builder()
                    .email("alma@alma.com")
                    .password(passwordEncoder.encode("P@ssw0rd"))
                    .username("alma")
                    .firstName("Alma")
                    .lastName("Piros")
                    .registrationDate(LocalDateTime.now())
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
            userRepository.save(userEntity);

        };

    }
}
