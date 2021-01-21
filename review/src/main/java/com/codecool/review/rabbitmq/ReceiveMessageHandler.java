package com.codecool.review.rabbitmq;

import com.codecool.review.model.DTO.*;
import com.codecool.review.service.ReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiveMessageHandler {

    private final ObjectMapper objectMapper;

    private final RabbitTemplate rabbitTemplate;

    private final ReviewService reviewService;

    @RabbitListener(queues = ConfigureRabbitMQ.DATES_QUEUE_NAME, errorHandler="rabbitRetryHandler")
    public void listen(String message) throws JsonProcessingException {
        RabbitMQDTO rabbitMQDTO = objectMapper.readValue(message, RabbitMQDTO.class);
        System.out.println(rabbitMQDTO);
        sendBackMessage(rabbitMQDTO.getIds());
    }

    //TODO sum
    @RabbitListener(queues = ConfigureRabbitMQ.REVIEW_QUEUE_NAME_2, errorHandler = "rabbitRetryHandler")
    public void listen2(String message) throws JsonProcessingException{
        System.out.println(message);
        Long id = Long.parseLong(message);
        List<ReviewResponseDTO> allReviewsByAccommodationId = reviewService.findAllReviewsByAccommodationId(id);
        Double averageRating = reviewService.getAverageRating(id);
        //sum
        System.out.println("EZKELL");
        System.out.println(message);
        sendBackMessage2(id, allReviewsByAccommodationId, averageRating);
    }

    public void sendBackMessage2(Long id, List<ReviewResponseDTO> reviews, Double averageRating) throws JsonProcessingException {
        ReviewDTO reviewListDTO = new ReviewDTO(id, averageRating, reviews);
        rabbitTemplate.convertAndSend(ConfigureRabbitMQ.REVIEW_EXCHANGE_NAME_3, "singlereview2.1", objectMapper.writeValueAsString(reviewListDTO));

    }

    // TODO sum
    public void sendBackMessage(List list) throws JsonProcessingException {
        long number;
        Double rating = 0.0;
        RabbitList rabbitList = new RabbitList();
        for(Object num: list){
            number =(long) num;
            rating = reviewService.getAverageRating(number);

            RabbitSendOut rabbitSendOut = new RabbitSendOut(number, rating);
            rabbitList.addElement(rabbitSendOut);
        }
        rabbitTemplate.convertAndSend(ConfigureRabbitMQ.REVIEW_EXCHANGE_NAME, "review.1", objectMapper.writeValueAsString(rabbitList));

    }



}
