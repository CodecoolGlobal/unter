package com.codecool.earthbnb.gateway.data_sample;

import com.codecool.earthbnb.gateway.model.entity.UserAddress;
import com.codecool.earthbnb.gateway.model.entity.UserEntity;
import com.codecool.earthbnb.gateway.model.entity.types.Gender;
import com.codecool.earthbnb.gateway.repository.UserRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@Component
public class UserCreator {
    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public UserCreator(UserRepository repository) {
        this.repository = repository;
    }

    public void initialize() {
        String[] firstNames = {
            "Luca",
            "Magda",
            "Márk",
            "Noémi",
            "Benec",
            "Bálint",
            "Bogi",
            "Andi",
            "Vivi",
            "Cudar"
        };

        String[] lastNames = {
            "Siba",
            "Fábián",
            "Werner",
            "György",
            "Fábián",
            "Simon",
            "Kiss",
            "Polyák",
            "Walter",
            "Fábián"
        };
        String[] emails = {
            "luca@dummy.com",
            "mag@dummy.com",
            "mark@dummy.com",
            "noemi@dummy.com",
            "benec@dummy.com",
            "balint@dummy.com",
            "bogi@dummy.com",
            "andi@dummy.com",
            "vivi@dummy.com",
            "cudar@dummy.com",
        };
        UserAddress[] addresses = {
            UserAddress.builder()
                .country("Hungary")
                .zipCode("2100")
                .city("Gödöllő")
                .street("Knézych Károly")
                .houseNumber("35")
                .build(),

            UserAddress.builder()
                .country("Hungary")
                .zipCode("1032")
                .city("Budapest")
                .street("Érc utca")
                .houseNumber("2")
                .build(),

            UserAddress.builder()
                .country("Hungary")
                .zipCode("1012")
                .city("Budapest")
                .street("Attila út")
                .houseNumber("182")
                .build(),

            UserAddress.builder()
                .country("Hungary")
                .zipCode("1122")
                .city("Budapest")
                .street("Városmajor utca")
                .houseNumber("84")
                .build(),

            UserAddress.builder()
                .country("Hungary")
                .zipCode("2000")
                .city("Szentendre")
                .street("Szomolnyica sétány")
                .houseNumber("23")
                .build(),

            UserAddress.builder()
                .country("Hungary")
                .zipCode("1033")
                .city("Budapest")
                .street("Vizimolnár utca")
                .houseNumber("23")
                .build(),

            UserAddress.builder()
                .country("Hungary")
                .zipCode("1039")
                .city("Budapest")
                .street("MargitLiget utca")
                .houseNumber("2")
                .build(),

            UserAddress.builder()
                .country("Hungary")
                .zipCode("1139")
                .city("Budapest")
                .street("Róbert Károly körút")
                .houseNumber("20")
                .build(),

            UserAddress.builder()
                .country("Hungary")
                .zipCode("1123")
                .city("Budapest")
                .street("Nyúl utca")
                .houseNumber("34")
                .build(),

            UserAddress.builder()
                .country("Hungary")
                .zipCode("1065")
                .city("Budapest")
                .street("Nagymező utca")
                .houseNumber("44")
                .build(),
        };

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");

        for (int i = 0; i < firstNames.length ; i++) {
            UserAddress address = addresses[i];
            NewUser newUser = NewUser.builder()
                .firstName(firstNames[i])
                .lastName(lastNames[i])
                .email(emails[i])
                .address(address)
                .password(passwordEncoder.encode("Password123!"))
                .birthDate(generateBirthDate())
                .phoneNumber("003698765432")
                .gender(randomGender())
                .roles(roles)
                .build();

            UserEntity userEntity = createUserEntity(newUser);
            address.setUserEntity(userEntity);

            repository.saveAndFlush(userEntity);
        }

    }

    private UserEntity createUserEntity(NewUser newUser) {
        return UserEntity.builder()
            .firstName(newUser.getFirstName())
            .lastName(newUser.getLastName())
            .email(newUser.getEmail())
            .password(newUser.getPassword())
            .registrationDate(LocalDateTime.now())
            .birthDate(newUser.getBirthDate())
            .phoneNumber(newUser.getPhoneNumber())
            .gender(newUser.getGender())
            .address(newUser.getAddress())
            .roles(newUser.getRoles())
            .build();
    }

    public  LocalDate generateBirthDate() {
        long startEpochDay = LocalDate.of(1980, 1, 1).toEpochDay();
        long endEpochDay = LocalDate.of(2001, 1, 1).toEpochDay();
        long randomDay = ThreadLocalRandom
            .current()
            .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    public Gender randomGender() {
        Random random = new Random();
        int x = random.nextInt(Gender.values().length);
        return Gender.values()[x];
    }

}
