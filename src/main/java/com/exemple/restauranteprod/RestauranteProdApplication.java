package com.exemple.restauranteprod;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exemple.restauranteprod.service.OrderService;

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
            System.out.println("1 - Enviar prato");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Nome do prato: ");
                String prato = scanner.nextLine();

                System.out.print("Categoria [pratos_principais | bebidas | sobremesas]: ");
                String category = scanner.nextLine();

                String mensagem = String.format("[%s] %s: %s", java.time.LocalDateTime.now().format(
                        java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")), nomeChef, prato);

                orderService.sendOrder(mensagem, category);
            }
        } while (option != 0);

        System.exit(0);
    }
}