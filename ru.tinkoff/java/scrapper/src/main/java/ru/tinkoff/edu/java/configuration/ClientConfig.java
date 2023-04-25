package ru.tinkoff.edu.java.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ru.tinkoff.edu.java.webclients.outside.implementations.GitHubClientBaseImpl;
import ru.tinkoff.edu.java.webclients.outside.implementations.StackOverFlowClientBaseImpl;
import ru.tinkoff.edu.java.webclients.outside.interfaces.GitHubClient;
import ru.tinkoff.edu.java.webclients.outside.interfaces.StackOverFlowClient;


@ConfigurationProperties(prefix = "client", ignoreUnknownFields = false)
public record ClientConfig(String gitHubBaseUrl, String stackOverFlowBaseUrl) {
    @Bean
    public @NotNull GitHubClient gitHubClient() {
        return new GitHubClientBaseImpl(gitHubBaseUrl);
    }

    @Bean
    public @NotNull StackOverFlowClient stackOverflowClient() {
        return new StackOverFlowClientBaseImpl(stackOverFlowBaseUrl);
    }


}