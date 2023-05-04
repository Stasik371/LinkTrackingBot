package ru.tinkoff.edu.java.bot.webclients.builders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.ApiErrorResponse;

public class WebClientBuilderStatusCodeHandlers {

    static Mono<ClientResponse> exchangeFilterResponseProcessor(ClientResponse response) {
        var status = response.statusCode();
        if (HttpStatus.BAD_REQUEST.equals(status) || HttpStatus.NOT_FOUND.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> {
                        try {
                            return Mono.error(new ObjectMapper()
                                    .readValue(body, ApiErrorResponse.class));
                        } catch (JsonProcessingException e) {
                            return Mono.error(new RuntimeException("Can't serialize json from request"));
                        }
                    });
        }
        return Mono.just(response);
    }
}