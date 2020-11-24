package com.codecool.accomodation.controller;

import com.codecool.accomodation.entity.Accommodation;
import com.codecool.accomodation.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService service;

}
