package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.java.bot.webclients.implementations.ScrapperClientImpls;
import ru.tinkoff.edu.java.bot.webclients.interfaces.ScrapperClient;

@Validated
@ConfigurationProperties(prefix = "client", ignoreUnknownFields = false)
public record ClientsConfig(String baseUrl) {

    @Bean
    public ScrapperClient scrapperClient() {
        return new ScrapperClientImpls(baseUrl);
    }
}
