package ru.tinkoff.edu.java.bot.webclients.builders;

import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

public class LinksClientBuilder implements WebClientBuilder {
    private final String baseUrlFromProperties = "http:localhost:8080";
    @Override
    public final WebClient build(String baseUrl) {
        WebClient.Builder webClient;
        if (baseUrl == null || baseUrl.isEmpty()) webClient = WebClient
                .builder()
                .baseUrl(baseUrlFromProperties);
        else webClient = WebClient
                .builder()
                .baseUrl(baseUrl);
        return webClient
                .filter(ExchangeFilterFunction
                        .ofResponseProcessor(WebClientBuilderStatusCodeHandlers::exchangeFilterResponseProcessor))
                .build();
    }

}