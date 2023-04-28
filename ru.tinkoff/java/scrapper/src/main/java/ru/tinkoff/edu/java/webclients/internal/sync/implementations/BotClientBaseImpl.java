package ru.tinkoff.edu.java.webclients.internal.sync.implementations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.webclients.internal.dto.LinkUpdate;
import ru.tinkoff.edu.java.webclients.internal.sync.interfaces.BotClient;

@Component
@Getter
@Setter
public class BotClientBaseImpl implements BotClient {

    private final WebClient webClient;

    @Value("${app.bot-base-url}")
    private String baseUrl;

    public BotClientBaseImpl() {
        webClient = WebClient
                .builder()
                .baseUrl(baseUrl)
                .build();

    }

    public BotClientBaseImpl(String url) {
        webClient = WebClient
                .builder()
                .baseUrl(url)
                .build();
    }

    @Override
    public HttpStatus sendUpdates(LinkUpdate linkUpdate) {
        return webClient.post()
                .uri("/updates")
                .body(Mono.just(linkUpdate), LinkUpdate.class)
                .retrieve()
                .bodyToMono(HttpStatus.class)
                .block();
    }
}
