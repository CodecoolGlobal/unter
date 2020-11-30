package com.codecool.accomodation.controller;

import com.codecool.accomodation.model.entity.Accommodation;
import com.codecool.accomodation.service.SearchService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor
public class SearchController {
    private SearchService searchService;

    /**
     * TODO: rewrite to return wrapper class with List<SimpleSearchResultDTO> within it
     * TODO: rewrite to take location parameters from the search query string
     * TODO: make coordinates a separate class! Coordinates should always go together.
     */
    @GetMapping
    public List<Accommodation> simpleSearch(Double longitude, Double lattitude, Double radius) {
        return searchService.getAllAccommodationInRadius(longitude, lattitude, radius);
    }
}
