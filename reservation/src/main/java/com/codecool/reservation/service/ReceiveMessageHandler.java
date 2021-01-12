package com.codecool.reservation.service;

import com.codecool.reservation.rabbitmq.ConfigureRabbitMQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiveMessageHandler {

    @RabbitListener(queues = ConfigureRabbitMQ.LOCATION_QUEUE_NAME)
    public void listen(String message){
        System.out.println("Consuming.." + message);
    }
//
//    public void handleLocationMessage(String messageBody){
//        log.info("HandleMessage!!!");
//        log.info(messageBody);
//    }
}
