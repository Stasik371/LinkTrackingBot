package ru.tinkoff.edu.java.webclients.outside.builders;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.webclients.WebClientBuilder;


public class StackOverFlowWebClientBuilder implements WebClientBuilder {
    @Value("${client.stackOverFlowApiBaseUrl}")
    private String baseUrlFromProperties;


    @Override
    public WebClient build(String baseUrl) {
        WebClient.Builder webClient;
        if (baseUrl == null || baseUrl.isEmpty()) webClient = WebClient.builder().baseUrl(baseUrlFromProperties);
        else webClient = WebClient.builder().baseUrl(baseUrl);
        return webClient
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Accept-Encoding", "gzip")
                .build();
    }

}
