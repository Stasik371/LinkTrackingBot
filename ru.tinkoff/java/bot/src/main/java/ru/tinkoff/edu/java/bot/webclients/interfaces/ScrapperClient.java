package ru.tinkoff.edu.java.bot.webclients.interfaces;

import org.springframework.http.HttpStatus;
import ru.tinkoff.edu.java.bot.webclients.dto.link.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.webclients.dto.link.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.LinkResponse;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.ListLinksResponse;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.RemoveLinkResponse;

public interface ScrapperClient {
    ListLinksResponse getAllLinks(long tgChatId);

    RemoveLinkResponse deleteLink(long tgChatId, RemoveLinkRequest linkRequest);

    LinkResponse addLink(long tgChatId, AddLinkRequest linkRequest);

    HttpStatus registerChat(long id);

    HttpStatus deleteChat(long id);
}