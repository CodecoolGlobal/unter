package com.codecool.reservation.rabbitmq;

import com.codecool.reservation.service.ReceiveMessageHandler;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;

public class ConfigureRabbitMQ {

    public static final String LOCATION_QUEUE_NAME = "locationQueue";

//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory
//            , MessageListenerAdapter messageListenerAdapter){
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(LOCATION_QUEUE_NAME);
//        container.setMessageListener(messageListenerAdapter);
//        return container;
//    }
//
//    @Bean
//    MessageListenerAdapter listenerAdapter(ReceiveMessageHandler handler){
//        return new MessageListenerAdapter(handler, "handleLocationMessage");
//    }

}
