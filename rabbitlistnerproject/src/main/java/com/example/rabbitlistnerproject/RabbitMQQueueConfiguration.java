package com.example.rabbitlistnerproject;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQQueueConfiguration {

    @Bean                                               // Creating new Queue
    Queue exampleQueue() {
        return new Queue("ExampleQueue", false);
    }

    @Bean                                               // Creating Queue using Queue Builder Methods
    Queue exampleanotherQueue() {
        return QueueBuilder.durable("Example2ndQueue")
                .autoDelete()
                .exclusive()
                .build();
    }
}
