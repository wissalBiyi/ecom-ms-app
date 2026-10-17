package net.biyi.customerservice;

import net.biyi.customerservice.entities.Customer;
import net.biyi.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder()
                    .name("Wissal").email("sola@gmail.com").build());
            customerRepository.save(Customer.builder()
                    .name("Douaae").email("douaae@gmail.com").build());
            customerRepository.save(Customer.builder()
                    .name("Amine").email("Amine@gmail.com").build());
        };
    }
}