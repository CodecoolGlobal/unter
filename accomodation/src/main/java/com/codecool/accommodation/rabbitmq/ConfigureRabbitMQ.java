package com.codecool.accommodation.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureRabbitMQ {

    public static final String LOCATION_EXCHANGE_NAME = "locationExchange";
    public static final String LOCATION_QUEUE_NAME = "locationQueue";
    public static final String DATES_QUEUE_NAME = "datesQueue";
    public static final String REVIEW_EXCHANGE_NAME_2 = "reviewExchange2";
    public static final String REVIEW_QUEUE_NAME = "reviewQueue";
    public static final String REVIEW_QUEUE_NAME_3 = "reviewQueue3";

    @Bean
    Queue createLocationQueue(){
        return new Queue(LOCATION_QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(LOCATION_EXCHANGE_NAME);
    }

    @Bean
    Binding locationBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("location.#");
    }



}
