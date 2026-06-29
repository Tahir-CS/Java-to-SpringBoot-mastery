package com.ledger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ResilientPaymentApplication {
    public static void main(String[] args) { SpringApplication.run(ResilientPaymentApplication.class, args); }
}
