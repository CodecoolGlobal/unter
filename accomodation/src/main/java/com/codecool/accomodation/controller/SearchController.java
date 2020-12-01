package com.codecool.accomodation.controller;

import com.codecool.accomodation.model.DTO.DTOWrapper;
import com.codecool.accomodation.model.DTO.CoordinateDTO;
import com.codecool.accomodation.service.SearchService;
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

    /**
     * TODO: rewrite to return wrapper class with List<SimpleSearchResultDTO> within it
     * TODO: make coordinates a separate class! Coordinates should always go together.
     */
    @GetMapping
    public DTOWrapper simpleSearch(
            @RequestParam Double longitude,
            @RequestParam Double latitude,
            @RequestParam Double radius
    ) {
        return searchService.getAllAccommodationInRadius(new CoordinateDTO(latitude, longitude), radius);
    }
}
