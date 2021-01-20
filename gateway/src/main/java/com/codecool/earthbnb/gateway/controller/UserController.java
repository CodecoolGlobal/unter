package com.codecool.earthbnb.gateway.controller;

import com.codecool.earthbnb.gateway.model.DTO.ProfileDTO;
import com.codecool.earthbnb.gateway.model.DTO.PublicUserDTO;
import com.codecool.earthbnb.gateway.service.UserService;
import com.codecool.earthbnb.gateway.service.validation.annotation.ValidLong;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/get-user-data/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PublicUserDTO getUserData(@PathVariable Long userId, HttpServletResponse response) {
        PublicUserDTO userDTO = userService.getPublicUserDataByUserId(userId);
        return userDTO;
    }


    @GetMapping(value = "/get-user-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public PublicUserDTO getLoggedInUserData(HttpServletRequest request, HttpServletResponse response){
        PublicUserDTO userDTO = userService.getLoggedInUserData(request);
        return userDTO;
    }

    @PostMapping("/save-profile-data/{userId}")
    public void saveProfileData(@PathVariable Long userId, @RequestBody ProfileDTO profileDTO){
        userService.saveProfileData(userId, profileDTO);
    }

}
