package net.biyi.billingservice;

import net.biyi.billingservice.entities.Bill;
import net.biyi.billingservice.entities.ProductItem;
import net.biyi.billingservice.repository.BillRepository;
import net.biyi.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Random;


@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository) {
        return args -> {

            List<Long> customersIds = List.of(1L, 2L, 3L);
            List<Long> productsIds = List.of(1L, 2L, 3L);
            customersIds.forEach(customer -> {
                Bill bill = Bill.builder()
                        .billingDate(new Date())
                        .customerId(customer)
                        .build();
                billRepository.save(bill);
                productsIds.forEach(productId -> {
                    ProductItem productItem = ProductItem.builder()
                            .bill(bill)
                            .productId(productId)
                            .quantity(1+new Random().nextInt(10))
                            .price(1000*Math.random()*600)
                            .build();
                    productItemRepository.save(productItem);
                });
            });
        };
    }
}
