package com.codecool.earthbnb.gateway.service.DAO;

import com.codecool.earthbnb.gateway.exception.UserExistsException;
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
                .birthDate(user.getBirthDate())
//                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    @Override
    public PublicUserDTO getLoggedInUserData(HttpServletRequest request) {
        String email = jwtTokenServices.getEmailFromToken(request);
        UserEntity user = userRepository.findDistinctByEmail(email);
        if(user != null){
            return PublicUserDTO.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .birthDate(user.getBirthDate())
                    .build();
        } else return null;
    }

    @Override
    public boolean register(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail()))
           throw new UserExistsException();

        UserEntity userEntity = UserEntity.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .registrationDate(LocalDateTime.now())
                .roles(Collections.singletonList("ROLE_USER"))
                .birthDate(userDTO.getBirthDate())
                .build();
        userRepository.save(userEntity);
        return true;
    }

    @Override
    public UserEntity getPublicUserDataByEmail(String email) {
        UserEntity user  = userRepository.findDistinctByEmail(email);
        return user;
    }

}
