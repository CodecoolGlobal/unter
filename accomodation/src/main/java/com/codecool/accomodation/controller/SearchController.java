package com.codecool.accomodation.controller;

import com.codecool.accomodation.model.entity.Accommodation;
import com.codecool.accomodation.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Accommodation> simpleSearch(
            @RequestParam Double longitude,
            @RequestParam Double latitude,
            @RequestParam Double radius
    ) {
        return searchService.getAllAccommodationInRadius(longitude, latitude, radius);
    }
}
