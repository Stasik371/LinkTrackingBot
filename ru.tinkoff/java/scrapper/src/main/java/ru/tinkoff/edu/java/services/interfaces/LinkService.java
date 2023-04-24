package ru.tinkoff.edu.java.services.interfaces;

import ru.tinkoff.edu.java.controllers.dto.response.LinkResponse;
import ru.tinkoff.edu.java.controllers.dto.response.ListLinksResponse;

import java.net.URI;

public interface LinkService {
    LinkResponse add(long tgChatId, URI url);

    LinkResponse remove(long tgChatId, URI url);

    ListLinksResponse listAll(long tgChatId);
}
