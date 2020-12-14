package com.codecool.earthbnb.gateway.controller;

import com.codecool.earthbnb.gateway.model.DTO.LoginDTO;
import com.codecool.earthbnb.gateway.security.JwtTokenServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;

    @GetMapping("")
    public void authenticate(HttpServletRequest request, HttpServletResponse response) {
        String tokenFromRequest = jwtTokenServices.getTokenFromRequest(request);
        boolean authenticated = jwtTokenServices.validateToken(tokenFromRequest);

            if (authenticated) {
                response.setStatus(200);

            } else {
                response.setStatus(401);
                try {
                    response.getWriter().println("user not authenticated");
                } catch (IOException e){
                    e.printStackTrace();
                }

            }

    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody LoginDTO loginData, HttpServletResponse response) {
        try {
            String username = loginData.getUsername();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, loginData.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());


            String token = jwtTokenServices.createToken(username, roles);
            ResponseCookie cookie = ResponseCookie
                    .from("authentication", token)
                    .maxAge(3600)  //1 hr
                    .path("/").httpOnly(true).secure(false).build();


            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("roles", roles);

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(model);
        } catch (AuthenticationException e) {
            response.setStatus(401);
            return null;
        }
    }


    @GetMapping(value = "/logout")
    public ResponseEntity logout(){
        ResponseCookie cookie = ResponseCookie
                .from("authentication", "")
                .maxAge(0)
                .path("/").httpOnly(true).secure(false).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("");
    }
}
