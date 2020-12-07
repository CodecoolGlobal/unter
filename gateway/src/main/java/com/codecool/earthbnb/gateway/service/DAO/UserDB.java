package com.codecool.earthbnb.gateway.service.DAO;

import com.codecool.earthbnb.gateway.model.DTO.PublicUserDTO;
import com.codecool.earthbnb.gateway.model.DTO.UserDTO;
import com.codecool.earthbnb.gateway.model.Response;
import com.codecool.earthbnb.gateway.model.entity.UserEntity;
import com.codecool.earthbnb.gateway.repository.UserRepository;
import com.codecool.earthbnb.gateway.security.JwtTokenServices;
import com.codecool.earthbnb.gateway.service.validation.ValidatorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collections;

@RequiredArgsConstructor
@Component
public class UserDB implements UserDAO {

    private final UserRepository userRepository;

    private final JwtTokenServices jwtTokenServices;

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();;

    private final ValidatorService validatorService;

    @Override
    public PublicUserDTO getPublicUserDataByUserId(Long userId) {
        UserEntity user = userRepository.findDistinctById(userId);

        if(user == null) return null;

        return PublicUserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    @Override
    public PublicUserDTO getLoggedInUserData(HttpServletRequest request) {
        String username = jwtTokenServices.getUsernameFromToken(request);
        UserEntity user = userRepository.findDistinctByUsername(username);
        if(user != null){
            return PublicUserDTO.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();
        } else return null;

    }

    @Override
    public Response register(UserDTO userDTO) {
        EmailValidator validator = EmailValidator.getInstance();

        if (!validator.isValid(userDTO.getEmail())) return new Response(false, "E-mail format not valid");

        if (userRepository.existsByEmail(userDTO.getEmail()))
            return new Response(false, "This email is already registered!");
        if (userRepository.existsByUsername(userDTO.getUsername()))
            return new Response(false, "This username is already taken!");
        if (!validatorService.validateRegistration(userDTO, 2, 20,  2, 20))
            return new Response(false, "Registration failed due to invalid credentials");


        UserEntity userEntity = UserEntity.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .username(userDTO.getUsername())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .registrationDate(LocalDateTime.now())
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
        userRepository.save(userEntity);
        return new Response(true, "success");
    }

}
