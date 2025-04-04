package com.exemple.restauranteprod.service;

import com.exemple.restauranteprod.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

        private final RabbitTemplate rabbitTemplate;

        public OrderService(RabbitTemplate rabbitTemplate) {
            this.rabbitTemplate = rabbitTemplate;
        }

        public void sendOrder(Order order, String category) {
            String routingKey = "pedidos." + category;
            rabbitTemplate.convertAndSend("pedidos-exchange", routingKey, order.toString());
            System.out.println("Order send!");
        }
    }



