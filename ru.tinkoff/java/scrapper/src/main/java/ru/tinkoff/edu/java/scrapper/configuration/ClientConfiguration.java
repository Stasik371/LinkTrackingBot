package ru.tinkoff.edu.java.scrapper.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.java.scrapper.webclients.internal.implementations.BotClientBaseImpl;
import ru.tinkoff.edu.java.scrapper.webclients.internal.interfaces.BotClient;
import ru.tinkoff.edu.java.scrapper.webclients.outside.implementations.GitHubClientBaseImpl;
import ru.tinkoff.edu.java.scrapper.webclients.outside.implementations.StackOverFlowClientBaseImpl;
import ru.tinkoff.edu.java.scrapper.webclients.outside.interfaces.GitHubClient;
import ru.tinkoff.edu.java.scrapper.webclients.outside.interfaces.StackOverFlowClient;

@Validated
@ConfigurationProperties(prefix = "client", ignoreUnknownFields = false)
public record ClientConfiguration(String gitHubBaseUrl, String stackOverFlowBaseUrl, String botBaseUrl) {
    @Bean
    public @NotNull GitHubClient gitHubClient() {
        return new GitHubClientBaseImpl(gitHubBaseUrl);
    }

    @Bean
    public @NotNull StackOverFlowClient stackOverflowClient() {
        return new StackOverFlowClientBaseImpl(stackOverFlowBaseUrl);
    }

    @Bean
    public @NotNull BotClient botClient(){
        return new BotClientBaseImpl(botBaseUrl);
    }
}