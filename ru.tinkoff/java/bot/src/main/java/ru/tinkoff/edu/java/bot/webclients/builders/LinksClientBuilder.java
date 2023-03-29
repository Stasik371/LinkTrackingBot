package ru.tinkoff.edu.java.bot.webclients.builders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.webclients.dto.link.request.ApiErrorResponse;

import java.util.Arrays;
import java.util.Map;

public class LinksClientBuilder implements WebClientBuilder {
    private String baseUrlFromProperties;


    @SafeVarargs
    @Override
    public final WebClient build(String baseUrl, Map<String, String>... headers) {
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
                .defaultHeader("Tg-Chat-Id", Arrays
                        .stream(headers)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Required header Tg-Chat-Id is missing"))
                        .get("Tg-Chat-Id"))
                .build();
    }

}
