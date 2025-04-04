package com.exemple.restauranteprod.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String id;
    private String name;
    private String description;
    private double price;
    private int cookingTime;
    private String chefName;
    private String timestamp;

    public void generateTimestamp() {
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("[%s] %s : %s | %s - %s | R$ %.2f | %d min",
                timestamp, chefName, id, name, description, price, cookingTime);
    }
}
