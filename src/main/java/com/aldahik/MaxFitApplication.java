package com.aldahik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MaxFitApplication {
    public static void main (String[] args) {
        SpringApplication.run(MaxFitApplication.class, args);
    }
    @GetMapping
    public String helloApp() {
        return "Hello Ghathan";
    }
}