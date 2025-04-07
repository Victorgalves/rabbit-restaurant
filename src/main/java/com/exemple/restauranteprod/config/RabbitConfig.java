package com.exemple.restauranteprod.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${app.exchange}")
    private String exchangeName;

    public static String EXCHANGE_NAME;

    @Bean
    public TopicExchange exchange() {
        EXCHANGE_NAME = exchangeName;
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue pratosPrincipaisQueue() {
        return new Queue("pratos_principais");
    }

    @Bean
    public Queue bebidasQueue() {
        return new Queue("bebidas");
    }

    @Bean
    public Queue sobremesasQueue() {
        return new Queue("sobremesas");
    }

    @Bean
    public Binding pratosPrincipaisBinding(Queue pratosPrincipaisQueue, TopicExchange exchange) {
        return BindingBuilder.bind(pratosPrincipaisQueue).to(exchange).with("pedidos.pratos_principais");
    }

    @Bean
    public Binding bebidasBinding(Queue bebidasQueue, TopicExchange exchange) {
        return BindingBuilder.bind(bebidasQueue).to(exchange).with("pedidos.bebidas");
    }

    @Bean
    public Binding sobremesasBinding(Queue sobremesasQueue, TopicExchange exchange) {
        return BindingBuilder.bind(sobremesasQueue).to(exchange).with("pedidos.sobremesas");
    }
}
