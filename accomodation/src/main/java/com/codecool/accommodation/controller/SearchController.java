package com.codecool.accommodation.controller;

import com.codecool.accommodation.model.DTO.CoordinateDTO;
import com.codecool.accommodation.model.wrapper.AccommodationDTOWrapper;
import com.codecool.accommodation.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping
    public AccommodationDTOWrapper simpleSearch(
            @RequestParam Double longitude,
            @RequestParam Double latitude,
            @RequestParam Double radius
    ) {
        return searchService.getAccommodationsInRadius(new CoordinateDTO(latitude, longitude), radius);
    }
}
