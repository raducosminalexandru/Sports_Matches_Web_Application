package com.example.postgresqlprogram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.postgresqlprogram.model")
@ComponentScan(basePackages = "com.example")
public class PostgresqlProgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostgresqlProgramApplication.class, args);
    }
}
