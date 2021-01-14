package com.codecool.reservation.rabbitmq;

import com.codecool.reservation.service.ReceiveMessageHandler;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Column;

@Configuration
public class ConfigureRabbitMQ {

    public static final String LOCATION_QUEUE_NAME = "locationQueue";

    public static final String LOCATION_EXCHANGE_NAME = "locationExchange";

    public static final String DATES_EXCHANGE_NAME = "datesExchange";

    public static final String DATES_QUEUE_NAME = "datesQueue";

    @Bean
    Queue createLocationQueue(){
        return new Queue(DATES_QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(DATES_EXCHANGE_NAME);
    }

    @Bean
    Binding locationBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("dates.#");
    }

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
