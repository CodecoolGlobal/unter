package com.codecool.accommodation.controller;

import com.codecool.accommodation.model.DTO.CoordinateDTO;
import com.codecool.accommodation.model.DTO.RabbitMQDTO;
import com.codecool.accommodation.model.DTO.ResponseAccDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.repository.AccommodationRepository;
import com.codecool.accommodation.service.SearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;
    private final AccommodationRepository accommodationRepository;


    @GetMapping(produces = "application/json;charset=utf8")
    public List<ResponseAccDTO> simpleSearch(
            @RequestParam Double longitude,
            @RequestParam Double latitude,
            @RequestParam(required = false) Double radius,
            @RequestParam String startDate,
            @RequestParam String endDate
            ) throws JsonProcessingException {
        searchService.getAccommodationIdsInRadius(new CoordinateDTO(latitude, longitude), radius, LocalDate.parse(startDate), LocalDate.parse(endDate));
        System.out.println(searchService.getAllAcc());
        return searchService.getAllAcc();
    }

}
