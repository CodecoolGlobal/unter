package com.codecool.accommodation.service;

import com.codecool.accommodation.exception.NoDataFoundException;
import com.codecool.accommodation.model.DTO.*;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.model.wrapper.AccommodationDTOWrapper;
import com.codecool.accommodation.rabbitmq.ConfigureRabbitMQ;
import com.codecool.accommodation.service.DAO.AccommodationDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NullArgumentException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationService {
    private static ReviewDTO reviewDTO;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final DTOCreator creator;
    private final AccommodationDAO accommodationDAO;


    public AccommodationDTOWrapper getAllAccommodation(Long hostId) {
        if (accommodationDAO.findAllByHostId(hostId) == null) {
            throw new NoDataFoundException();
        }
        return creator.turnInputListToAccommodationDTO(accommodationDAO.findAllByHostId(hostId));
    }

    public void saveNewAccommodation(NewAccommodationDTO newAccommodationDTO) {
        accommodationDAO.saveNewAccommodation(newAccommodationDTO);
    }

    public void deleteAccommodation(Long accommodationId) {
        if (accommodationDAO.isExisted(accommodationId)){
            accommodationDAO.deleteAccommodation(accommodationId);
        } else {
            throw new NoDataFoundException();
        }

    }

    public void updateAccommodation(Long accommodationId, NewAccommodationDTO newAccommodationDTO) {
        try {
            accommodationDAO.updateAccommodation(accommodationId, newAccommodationDTO);
        } catch (NullArgumentException exception) {
            exception.printStackTrace();
        }
    }

    public List<Accommodation> findAll() {
        return accommodationDAO.findAll();
    }


    public ResponseAccDTO findAccommodationById(Long accommodationId) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(ConfigureRabbitMQ.REVIEW_EXCHANGE_NAME_2, "singlereview.1", objectMapper.writeValueAsString(accommodationId));
        ResponseAccDTO accommodation = accommodationDAO.findAccommodationById(accommodationId);
        System.out.println(reviewDTO);
        if(reviewDTO == null){
            findAccommodationById(accommodationId);
        } else {
            accommodation.setRating(reviewDTO.getRating());
            accommodation.setReviews(reviewDTO.getReviews());
            return accommodation;
        }
       return null;
    }

    public ResponseAccDTO findAccById(Long accId){
        return accommodationDAO.findAccommodationById(accId);
    }


    public List<String> findAllAccommodationTypes() {
        return accommodationDAO.findAllAccommodationTypes();
    }

    public List<String> findAllRoomTypes() {
        return accommodationDAO.findAllRoomTypes();
    }

    public List<String> findAllBedTypes() {
        return accommodationDAO.findAllBedTypes();
    }

    @RabbitListener(queues = ConfigureRabbitMQ.REVIEW_QUEUE_NAME_3, errorHandler="rabbitRetryHandler")
    public void reviewListen(String message) throws JsonProcessingException {
        ReviewDTO rabbitList = objectMapper.readValue(message, ReviewDTO.class);
        assignReviewDTO(rabbitList);
        System.out.println("HELLOO");
        System.out.println(rabbitList);
    }

    public void assignReviewDTO(ReviewDTO myreviewDTO){
        reviewDTO = myreviewDTO;
    }


}
