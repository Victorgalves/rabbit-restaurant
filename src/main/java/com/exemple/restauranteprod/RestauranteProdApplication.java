package com.exemple.restauranteprod;

import com.exemple.restauranteprod.model.Order;
import com.exemple.restauranteprod.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
public class RestauranteProdApplication implements CommandLineRunner {

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(RestauranteProdApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o nome do chef: ");
        String nomeChef = scanner.nextLine();

        int option;

        do {
            System.out.println("\n== MENU CHEF ==");
            System.out.println("1 - Enviar novo order");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                Order order = new Order();
                order.setId(UUID.randomUUID().toString());

                System.out.print("Nome do prato: ");
                order.setName(scanner.nextLine());

                System.out.print("Descrição: ");
                order.setDescription(scanner.nextLine());

                System.out.print("Preço (R$): ");
                order.setPrice(scanner.nextDouble());

                System.out.print("Tempo de preparo (min): ");
                order.setCookingTime(scanner.nextInt());
                scanner.nextLine();

                System.out.print("Categoria [pratos_principais | bebidas | sobremesas]: ");
                String category = scanner.nextLine();

                order.setChefName(nomeChef);
                order.generateTimestamp();
                orderService.sendOrder(order, category);
            }
        } while (option != 0);
        System.exit(0);

    }
}
