package com.codecool.review.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureRabbitMQ {

    public static final String REVIEW_QUEUE_NAME = "reviewQueue";

    public static final String REVIEW_QUEUE_NAME_2 = "reviewQueue2";

    public static final String DATES_QUEUE_NAME = "datesQueue";

   public static final String REVIEW_EXCHANGE_NAME = "reviewExchange";

    public static final String REVIEW_EXCHANGE_NAME_3 = "reviewExchange3";



    @Bean
    Queue createReviewQueue(){
        return new Queue(REVIEW_QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(REVIEW_EXCHANGE_NAME);
    }

//    @Bean
//    DirectExchange directExchange(){
//        return new DirectExchange(REVIEW_EXCHANGE_NAME_2);
//    }

    @Bean
    Binding reviewBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("review.#");
    }

//    @Bean
//    Binding review2Binding(Queue queue, DirectExchange directExchange){
//        return BindingBuilder.bind(queue).to(directExchange).with("singlereview.#");
//    }





}
