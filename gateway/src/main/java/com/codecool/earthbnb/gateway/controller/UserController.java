package com.codecool.earthbnb.gateway.controller;

import com.codecool.earthbnb.gateway.model.DTO.PublicUserDTO;
import com.codecool.earthbnb.gateway.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

            if(userDTO == null){
                response.setStatus(401);
                try {
                    response.getWriter().println("no user available with this id");
                } catch (IOException e){
                    e.printStackTrace();
                }
                return null;
            } else{
                return userDTO;
            }




    }

    @GetMapping(value = "/get-user-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public PublicUserDTO getLoggedInUserData(HttpServletRequest request, HttpServletResponse response){
        PublicUserDTO userDTO = userService.getLoggedInUserData(request);

            if(userDTO == null){
                response.setStatus(401);
                try {
                    response.getWriter().println("no user available");
                } catch (IOException e){
                    e.printStackTrace();
                }
                return null;
            } else {
                return userDTO;
            }
    }
}
