package com.codecool.earthbnb.gateway.security;

import com.codecool.earthbnb.gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Component
@RestController
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    /**
     * Loads the user from the DB and converts it to Spring Security's internal User object.
     * Spring will call this code to retrieve a user upon login from the DB.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        com.codecool.earthbnb.gateway.model.entity.UserEntity user = userRepository.findDistinctByEmail(email);
//                .orElseThrow(() -> new UsernameNotFoundException("Username: " + email + " not found"));



        if (user == null) {
            throw new UsernameNotFoundException("Email: " + email + " not found");
        }

        return new User(user.getEmail(), user.getPassword(),
                user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}