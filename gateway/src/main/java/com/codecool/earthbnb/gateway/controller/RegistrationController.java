package com.codecool.earthbnb.gateway.controller;

import com.codecool.earthbnb.gateway.model.DTO.UserDTO;
import com.codecool.earthbnb.gateway.model.Response;
import com.codecool.earthbnb.gateway.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/reg")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("")
    public void doRegistration(HttpServletResponse response, @RequestBody @Valid UserDTO userDTO) {
        userService.register(userDTO);
    }
}
