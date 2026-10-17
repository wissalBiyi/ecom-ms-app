package net.biyi.inventoryservice;

import net.biyi.inventoryservice.entities.Product;
import net.biyi.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                    .name("Computer")
                    .price(34000.)
                    .quantity(12)
                    .build());
            productRepository.save(Product.builder()
                    .name("Printer")
                    .price(1200.)
                    .quantity(10)
                    .build());
            productRepository.save(Product.builder()
                    .name("Smart Phone")
                    .price(11000.)
                    .quantity(6)
                    .build());
        };
    }
}
