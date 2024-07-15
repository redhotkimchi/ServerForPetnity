package com.example.petnity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PetnityApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetnityApplication.class, args);
    }

}
