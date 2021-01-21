package com.codecool.review.rabbitmq;

import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

public class ConfigureRabbitMQ {

    public static final String LOCATION_EXCHANGE_NAME = "locationExchange";
    public static final String LOCATION_QUEUE_NAME = "locationQueue";

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
