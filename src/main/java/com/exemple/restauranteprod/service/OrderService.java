package com.exemple.restauranteprod.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final RabbitTemplate rabbitTemplate;

    public OrderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrder(String prato, String category) {
        String routingKey = "pedidos." + category;
        rabbitTemplate.convertAndSend("pedidos-exchange", routingKey, prato);
        System.out.println("Prato enviado!");
    }
}