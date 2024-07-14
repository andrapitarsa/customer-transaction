package com.andra.transaction;

import com.andra.transaction.model.Customer;
import com.andra.transaction.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class TransactionApplication {

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }

    @PostConstruct
    public void initializeData() {
        boolean isExists = customerRepository.existsByIdIn(Arrays.asList(1, 2));
        if (!isExists) {
            createInitialCustomers();
        }
    }

    private void createInitialCustomers() {
        // Create Customer 1
        Customer customer1 = new Customer(
                1,
                LocalDateTime.now(),
                "admin",
                false,
                LocalDateTime.now(),
                "admin",
                BigDecimal.valueOf(5000000),
                "customer1@example.com",
                "A Rachman",
                "081262771623",
                "rachman"
        );

        // Create Customer 2
        Customer customer2 = new Customer(
                2,
                LocalDateTime.now(),
                "admin",
                false,
                LocalDateTime.now(),
                "admin",
                BigDecimal.valueOf(1000000),
                "customer2@example.com",
                "Caca Caroline",
                "08122635251",
                "cacacaroline"
        );

        customerRepository.save(customer1);
        customerRepository.save(customer2);
    }
}
