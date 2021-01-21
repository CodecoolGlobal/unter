package com.codecool.reservation.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureRabbitMQReview {

    public static final String REVIEW_EXCHANGE_NAME = "reviewExchange";


//    @Bean
//    DirectExchange exchange(){
//        return new DirectExchange(REVIEW_EXCHANGE_NAME);
//    }
//
//    @Bean
//    Binding locationBinding(Queue queue, DirectExchange
//            directExchange){
//        return BindingBuilder.bind(queue).to(directExchange).with("review.#");
//    }

}
