package com.codecool.accommodation.service;

import com.codecool.accommodation.model.DTO.CoordinateDTO;
import com.codecool.accommodation.model.DTO.RabbitMQDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.rabbitmq.ConfigureRabbitMQ;
import com.codecool.accommodation.repository.AccommodationRepository;
import com.codecool.accommodation.service.DAO.CoordinateDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private static RabbitMQDTO rabbitMessage;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final DTOCreator creator;
    private final CoordinateDAO coordinateDAO;
    private static final double DEFAULT_SEARCH_DISTANCE = 0D;
    private static final String NO_COORDINATE_MESSAGE = "Coordinates cannot be null.";
    private final AccommodationRepository repository;

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

    public void getAccommodationIdsInRadius(CoordinateDTO coordinate, Double searchRadius, LocalDate startDate, LocalDate endDate) throws JsonProcessingException {
        searchRadius = searchRadius == null ? DEFAULT_SEARCH_DISTANCE : searchRadius;
        if (coordinate.getLatitude() == null || coordinate.getLongitude() == null) {
           // return ResponseEntity.status(400).body(NO_COORDINATE_MESSAGE);
        }
        List<Long> allAccommodationIdsInRadius = new ArrayList<>();
        List<Coordinate> foundCoordinates = coordinateDAO.getAllByDistanceFromCoordinate(coordinate, searchRadius);
        if (!foundCoordinates.isEmpty()) {
            for (Coordinate actualCoordinate : foundCoordinates) {
                allAccommodationIdsInRadius.add(actualCoordinate.getAccommodation().getId());
            }
        }
//        var json = objectMapper.writeValueAsString(allAccommodationIdsInRadius);
        ObjectNode object = objectMapper.createObjectNode();
        ArrayNode ids = objectMapper.createArrayNode();
        for(long num: allAccommodationIdsInRadius){
            ids.add(num);
        }

        object.set("ids", ids);

        object.put("startDate", String.valueOf(startDate));
        object.put("endDate", String.valueOf(endDate));
        rabbitTemplate.convertAndSend(ConfigureRabbitMQ.LOCATION_EXCHANGE_NAME, "location.1", objectMapper.writeValueAsString(object));



    }

    @RabbitListener(queues = ConfigureRabbitMQ.DATES_QUEUE_NAME, errorHandler="rabbitRetryHandler")
    public void listen(String message) throws JsonProcessingException {
        RabbitMQDTO rabbitList = objectMapper.readValue(message, RabbitMQDTO.class);

        assignRabbitMessage(rabbitList);

    }

    public void assignRabbitMessage(RabbitMQDTO rabbitList) {
        rabbitMessage = rabbitList;
    }

    public RabbitMQDTO getMessage(){
        return rabbitMessage;
    }

    public List<Accommodation> getAllAcc(){
        List<Accommodation> accommodations = new ArrayList<>();
        if(rabbitMessage != null){
            for(Long id: rabbitMessage.getIds()){
                Accommodation accommodation = repository.findAccommodationById(id);
                accommodations.add(accommodation);
            }
        }

        return accommodations;
    }


}
