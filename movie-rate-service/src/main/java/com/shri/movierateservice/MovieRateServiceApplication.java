package com.shri.movierateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieRateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieRateServiceApplication.class, args);
    }

}
