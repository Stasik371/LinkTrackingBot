package ru.tinkoff.edu.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.configuration.ClientConfig;


@SpringBootApplication
@EnableConfigurationProperties({ApplicationConfig.class, ClientConfig.class})
public class ScrapperApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScrapperApplication.class, args);
    }
}
