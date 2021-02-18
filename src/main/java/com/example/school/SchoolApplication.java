package com.example.school;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SchoolApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(SchoolApplication.class, args);
        //SpringApplication.run(SecuringWebApplication.class, args);
    }
}
