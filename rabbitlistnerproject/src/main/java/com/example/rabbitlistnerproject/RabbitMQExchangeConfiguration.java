package com.example.rabbitlistnerproject;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQExchangeConfiguration {

    @Bean                                       // Creating new Exchange
    Exchange exampleExchange() {
        return new TopicExchange("ExampleExchange");
    }

    @Bean                                       // Creating new Direct Exchange using Exchange Builder Methods
    Exchange example2ndExchange() {
        return ExchangeBuilder.directExchange("Example2ndExchange")
                .autoDelete()
                .internal()
                .build();
    }

    @Bean                                        // Creating new Topic Exchange using Exchange Builder Methods
    Exchange newExchange() {
        return ExchangeBuilder.topicExchange("TopicTestExchange")
                .autoDelete()
                .durable(true)
                .internal()
                .build();
    }


    @Bean                                        // Creating new Fanout Exchange using Exchange Builder Methods
    Exchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange("FanoutTestExchange")
                .autoDelete()
                .durable(false)
                .internal()
                .build();
    }

    @Bean                                       // Creating new Headers Exchange using Exchange Builder Methods
    Exchange headersExchange() {
        return ExchangeBuilder.headersExchange("HeadersTestExchange")
                .internal()
                .durable(true)
                .ignoreDeclarationExceptions()
                .build();
    }


}
