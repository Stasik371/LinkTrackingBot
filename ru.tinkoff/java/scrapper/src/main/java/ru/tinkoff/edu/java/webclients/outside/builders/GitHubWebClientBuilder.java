package ru.tinkoff.edu.java.webclients.outside.builders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.webclients.WebClientBuilder;


public class GitHubWebClientBuilder implements WebClientBuilder {
    @Value("${client.gitHubBaseUrl}")
    private String baseUrlFromProperties;


    @Override
    public WebClient build(String baseUrl) {
        WebClient.Builder webClient;
        if (baseUrl == null || baseUrl.isEmpty()) webClient = WebClient.builder().baseUrl(baseUrlFromProperties);
        else webClient = WebClient.builder().baseUrl(baseUrl);
        return webClient
                .defaultHeader("Accept", "application/vnd.github+json")
                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
                .build();
    }
}
