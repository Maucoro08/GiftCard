package com.mauricioCoronado.giftCard_manager.giftCard.infrastructure.rabbitMQ.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfig {

    @Bean
    public Queue queue() {
        return new Queue("Grupo-Exito", true);
    }

}
