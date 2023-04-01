package ru.tinkoff.edu.java.bot.webclients.implementations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.bot.webclients.builders.LinksClientBuilder;
import ru.tinkoff.edu.java.bot.webclients.dto.link.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.webclients.dto.link.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.LinkResponse;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.ListLinksResponse;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.RemoveLinkResponse;
import ru.tinkoff.edu.java.bot.webclients.interfaces.LinksClient;

@Getter
@Setter
@Component
public class LinksClientImpl implements LinksClient {
    private final WebClient client;

    private Long tgChatId;
    private final String REQUIRED_HEADER = "Tg-Chat-Id";

    public LinksClientImpl() {
        client = new LinksClientBuilder().build(null);
    }

    public LinksClientImpl(String baseUrl) {
        client = new LinksClientBuilder().build(baseUrl);
    }

    @Override
    public ListLinksResponse getAllLinks(long tgChatId) {
        return client.get()
                .uri("/links")
                .header(REQUIRED_HEADER, Long.toString(tgChatId))
                .retrieve()
                .bodyToMono(ListLinksResponse.class)
                .block();
    }

    @Override
    public RemoveLinkResponse deleteLink(long tgChatId, RemoveLinkRequest linkRequest) {
        return client.delete()
                .uri("/links")
                .header(REQUIRED_HEADER, Long.toString(tgChatId))
                .retrieve()
                .bodyToMono(RemoveLinkResponse.class)
                .block();
    }

    @Override
    public LinkResponse addLink(long tgChatId, AddLinkRequest linkRequest) {
        return client.post()
                .uri("/links")
                .header(REQUIRED_HEADER, Long.toString(tgChatId))
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }
}
