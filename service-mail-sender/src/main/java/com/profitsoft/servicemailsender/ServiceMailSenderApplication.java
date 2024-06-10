package com.profitsoft.servicemailsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServiceMailSenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMailSenderApplication.class, args);
    }
}
