package com.codecool.reservation.service;

import com.codecool.reservation.model.DTO.RabbitMQDTO;
import com.codecool.reservation.rabbitmq.ConfigureRabbitMQ;
import com.codecool.reservation.rabbitmq.ConfigureRabbitMQReview;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiveMessageHandler {
    private final ObjectMapper objectMapper;

    private final RabbitTemplate rabbitTemplate;

    private final ReservationService reservationService;

    @RabbitListener(queues = ConfigureRabbitMQ.LOCATION_QUEUE_NAME)
    public void listen(String message) throws JsonProcessingException {
        RabbitMQDTO rabbitMQDTO = objectMapper.readValue(message, RabbitMQDTO.class);
        sendBackMessage(rabbitMQDTO.getIds(), rabbitMQDTO.getStartDate(), rabbitMQDTO.getEndDate());
    }

    public void sendBackMessage(List list, LocalDate startDate, LocalDate endDate) throws JsonProcessingException {
        long number;
        //List<Long> idsToSendBack = new ArrayList<>();

        ObjectNode object = objectMapper.createObjectNode();
        ArrayNode ids = objectMapper.createArrayNode();
        for(Object num: list){
            number =(long) num;
            if (!reservationService.checkIfHasReservationBetweenDates(number, startDate, endDate)){
                ids.add(number);
            }
        }

        object.set("ids", ids);

        //var json = objectMapper.writeValueAsString(idsToSendBack);
        System.out.println(objectMapper.writeValueAsString(object));
        //rabbitTemplate.convertAndSend(ConfigureRabbitMQReview.REVIEW_EXCHANGE_NAME, "review.1", objectMapper.writeValueAsString(object));
        rabbitTemplate.convertAndSend(ConfigureRabbitMQ.DATES_EXCHANGE_NAME, "dates.1", objectMapper.writeValueAsString(object));

    }
//
//    public void handleLocationMessage(String messageBody){
//        log.info("HandleMessage!!!");
//        log.info(messageBody);
//    }
}
