package ru.tinkoff.edu.java.webclients.internal.implementations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.webclients.internal.builders.BotClientBuilder;
import ru.tinkoff.edu.java.webclients.internal.dto.LinkUpdate;
import ru.tinkoff.edu.java.webclients.internal.interfaces.BotClient;

@Component
@Getter
@Setter
public class BotClientBaseImpl implements BotClient {
    private final WebClient client;

    public BotClientBaseImpl() {
        client = new BotClientBuilder().build(null);
    }

    public BotClientBaseImpl(String baseUrl) {
        client = new BotClientBuilder().build(baseUrl);
    }

    @Override
    public HttpStatus sendUpdates(LinkUpdate linkUpdate) {
        return client.post()
                .uri("/updates")
                .retrieve()
                .bodyToMono(HttpStatus.class)
                .block();
    }
}
