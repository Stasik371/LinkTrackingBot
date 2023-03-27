package ru.tinkoff.edu.java.scrapper.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.java.scrapper.webclients.implementations.GitHubClientBaseImpl;
import ru.tinkoff.edu.java.scrapper.webclients.implementations.StackOverFlowClientBaseImpl;
import ru.tinkoff.edu.java.scrapper.webclients.interfaces.GitHubClient;
import ru.tinkoff.edu.java.scrapper.webclients.interfaces.StackOverFlowClient;

@Validated
@ConfigurationProperties(prefix = "client", ignoreUnknownFields = false)
public record ClientConfiguration(String gitHubBaseUrl, String stackOverFlowBaseUrl) {
    @Bean
    public @NotNull GitHubClient gitHubClient() {
        return new GitHubClientBaseImpl(gitHubBaseUrl);
    }

    @Bean
    public @NotNull StackOverFlowClient stackOverflowClient() {
        return new StackOverFlowClientBaseImpl(stackOverFlowBaseUrl);
    }
}