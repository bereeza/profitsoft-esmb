package com.profitsoft.servicemailsender.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbit config file.
 */
@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_MAIL = "customer-mail-exchange";
    public static final String QUEUE_MAIL = "customer-mail-queue";

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_MAIL, true, false);
    }

    @Bean
    public Queue mailQueue() {
        return new Queue(QUEUE_MAIL, true);
    }

    @Bean
    public Binding binding(Queue mailQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(mailQueue).to(fanoutExchange);
    }
}