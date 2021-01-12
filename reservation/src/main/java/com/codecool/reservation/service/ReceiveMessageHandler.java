package com.codecool.reservation.service;

import com.codecool.reservation.rabbitmq.ConfigureRabbitMQ;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiveMessageHandler {
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = ConfigureRabbitMQ.LOCATION_QUEUE_NAME)
    public void listen(String message) throws JsonProcessingException {
        var list = objectMapper.readValue(message, List.class);
        sendBackMessage(list);
        System.out.println(list);
    }

    public static void sendBackMessage(List list){
        Long number;
        for(Object num: list){
            number = Long.parseLong((String) num);

        }
    }
//
//    public void handleLocationMessage(String messageBody){
//        log.info("HandleMessage!!!");
//        log.info(messageBody);
//    }
}
