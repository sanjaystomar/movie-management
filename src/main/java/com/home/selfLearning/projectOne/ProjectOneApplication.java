package com.home.selfLearning.projectOne;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectOneApplication.class, args);
    }


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
