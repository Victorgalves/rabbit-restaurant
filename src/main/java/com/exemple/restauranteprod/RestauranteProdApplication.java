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

        int option;
        do {
            System.out.println("\n== MENU CHEF ==");
            System.out.println("1 - Enviar prato");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            option = scanner.nextInt();
            scanner.nextLine();
            
            if (option == 1) {
                System.out.print("Nome: ");
                String prato = scanner.nextLine();

                System.out.print("Escolha uma categoria: ");
                System.out.print("1 - Pratos principais | 2 - Bebidas | Sobremesas]: ");
                String opcao = scanner.nextLine();
                String rota;
                String categoria;

                switch(opcao){
                    case "1":
                        rota = "pratos_principais";
                        categoria = "Pratos Principais";
                        break;
                    case "2":
                        rota = "bebidas";
                        categoria = "Bebidas";
                        break;
                    case "3":
                        rota = "sobremesas";
                        categoria = "Sobremesas";
                        break;
                    default:
                        System.out.println("Categoria inválida.");
                        continue;
                }

                String mensagem = String.format("[%s] %s: %s", java.time.LocalDateTime.now().format(
                        java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")), categoria, prato);

                orderService.sendOrder(mensagem, rota);
            }
        } while (option != 0);

        System.exit(0);
    }
}