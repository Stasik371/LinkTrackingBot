package ru.tinkoff.edu.java.bot.webclients.implementations;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.bot.webclients.builders.TGChatClientBuilder;
import ru.tinkoff.edu.java.bot.webclients.interfaces.TGChatClient;

public class TGChatClientImpl implements TGChatClient {
    private final WebClient client;

    public TGChatClientImpl(String baseUrl) {
        client = new TGChatClientBuilder().build(baseUrl);
    }

    public TGChatClientImpl() {
        client = new TGChatClientBuilder().build(null);
    }

    @Override
    public HttpStatus registerChat(long id) {
        return client.get()
                .uri("/tg-chat/{id}", id)
                .retrieve()
                .bodyToMono(HttpStatus.class)
                .block();
    }

    @Override
    public HttpStatus deleteChat(long id) {
        return client.delete()
                .uri("/tg-chat/{id}", id)
                .retrieve()
                .bodyToMono(HttpStatus.class)
                .block();
    }
}
