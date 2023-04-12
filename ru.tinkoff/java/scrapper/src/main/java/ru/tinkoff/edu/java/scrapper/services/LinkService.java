package ru.tinkoff.edu.java.scrapper.services;

import ru.tinkoff.edu.java.scrapper.controllers.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.controllers.dto.response.ListLinksResponse;

import java.net.URI;

public interface LinkService {
    LinkResponse add(long tgChatId, URI url);

    LinkResponse remove(long tgChatId, URI url);

    ListLinksResponse listAll(long tgChatId);
}
