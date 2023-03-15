package com.example.commhere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CommhereApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommhereApplication.class, args);
    }

}
