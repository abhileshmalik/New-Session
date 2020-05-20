package com.example.rabbitlistnerproject;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final String My_QUEUE = "MyQueue";

    @Bean                                               //This will create a Queue in Rabbit MQ in Exchanges
    Queue myQueue() {
        return new Queue(My_QUEUE, true);
    }

    @Bean
    Exchange myExchange() {
        return ExchangeBuilder.topicExchange("MyTopicExchange")
                .durable(true)
                .build();
    }

/*    @Bean                                           // Traditional way to create Bindings
    Binding binding() {
        return new Binding(My_QUEUE, Binding.DestinationType.QUEUE, "MyTopicExchange", "topic", null);
    }*/


    @Bean                                           // Creating Bindings using Binding Builders
    Binding newBinding() {
        return BindingBuilder
                .bind(myQueue())
                .to(myExchange())
                .with("topic")
                .noargs();
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");                          // Rabbit MQ Login Credentials
        cachingConnectionFactory.setPassword("guest");

        return cachingConnectionFactory;
    }

    @Bean
    MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(myQueue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListner());

        return simpleMessageListenerContainer;
    }

}
