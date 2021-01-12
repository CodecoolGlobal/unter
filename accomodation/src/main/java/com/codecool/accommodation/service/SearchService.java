package com.codecool.accommodation.service;

import com.codecool.accommodation.model.DTO.CoordinateDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.rabbitmq.ConfigureRabbitMQ;
import com.codecool.accommodation.service.DAO.CoordinateDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final RabbitTemplate rabbitTemplate;
    private final DTOCreator creator;
    private final CoordinateDAO coordinateDAO;
    private static final double DEFAULT_SEARCH_DISTANCE = 0D;
    private static final String NO_COORDINATE_MESSAGE = "Coordinates cannot be null.";

    public ResponseEntity<?> getAccommodationsInRadius(CoordinateDTO coordinate, Double searchRadius) {
        searchRadius = searchRadius == null ? DEFAULT_SEARCH_DISTANCE : searchRadius;
        if (coordinate.getLatitude() == null || coordinate.getLongitude() == null)
            return ResponseEntity.status(400).body(NO_COORDINATE_MESSAGE);

        List<Accommodation> allAccommodationsInRadius = new ArrayList<>();
        List<Coordinate> foundCoordinates = coordinateDAO.getAllByDistanceFromCoordinate(coordinate, searchRadius);
        if (!foundCoordinates.isEmpty()) {
            for (Coordinate actualCoordinate : foundCoordinates) {
                allAccommodationsInRadius.add(actualCoordinate.getAccommodation());
            }
        }
        return ResponseEntity.ok(creator.turnInputListToAccommodationDTO(allAccommodationsInRadius));
    }

    public ResponseEntity<?> getAccommodationIdsInRadius(CoordinateDTO coordinate, Double searchRadius) {
        searchRadius = searchRadius == null ? DEFAULT_SEARCH_DISTANCE : searchRadius;
        if (coordinate.getLatitude() == null || coordinate.getLongitude() == null)
            return ResponseEntity.status(400).body(NO_COORDINATE_MESSAGE);

        List<Long> allAccommodationIdsInRadius = new ArrayList<>();
        List<Coordinate> foundCoordinates = coordinateDAO.getAllByDistanceFromCoordinate(coordinate, searchRadius);
        if (!foundCoordinates.isEmpty()) {
            for (Coordinate actualCoordinate : foundCoordinates) {
                allAccommodationIdsInRadius.add(actualCoordinate.getAccommodation().getId());
            }
        }
        System.out.println(Arrays.toString(allAccommodationIdsInRadius.toArray()));
        rabbitTemplate.convertAndSend(ConfigureRabbitMQ.LOCATION_EXCHANGE_NAME, "location.1", allAccommodationIdsInRadius);

        return ResponseEntity.ok(allAccommodationIdsInRadius);
    }
}
