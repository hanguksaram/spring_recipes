package com.scalaboy.recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {""})
public class RecipessApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipessApplication.class, args);
    }
}
