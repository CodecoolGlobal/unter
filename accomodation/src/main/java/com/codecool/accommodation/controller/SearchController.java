package com.codecool.accommodation.controller;

import com.codecool.accommodation.model.DTO.CoordinateDTO;
import com.codecool.accommodation.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping(produces = "application/json;charset=utf8")
    public ResponseEntity<?> simpleSearch(
            @RequestParam Double longitude,
            @RequestParam Double latitude,
            @RequestParam(required = false) Double radius
    ) {
        return searchService.getAccommodationsInRadius(new CoordinateDTO(latitude, longitude), radius);
    }
}
