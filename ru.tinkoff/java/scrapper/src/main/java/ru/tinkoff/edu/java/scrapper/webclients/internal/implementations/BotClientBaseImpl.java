package ru.tinkoff.edu.java.scrapper.webclients.internal.implementations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.webclients.internal.builders.BotClientBuilder;
import ru.tinkoff.edu.java.scrapper.webclients.internal.dto.LinkUpdate;
import ru.tinkoff.edu.java.scrapper.webclients.internal.interfaces.BotClient;

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
    public LinkUpdate sendUpdates(LinkUpdate linkUpdate) {
        return client.post()
                .uri("/updates")
                .retrieve()
                .bodyToMono(LinkUpdate.class)
                .block();
    }
}
