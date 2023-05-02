package ru.tinkoff.edu.java.domain;

import ru.tinkoff.edu.java.domain.model.LinkModel;

import java.net.URI;
import java.util.List;

public interface LinkRepository {

    List<LinkModel> readAllToUpdate();

    List<LinkModel> readAllWithTgChatId(long tgChatId);

    List<LinkModel> readAll();

    URI delete(URI uri, long tgChatId);

    LinkModel add(LinkModel model);

    Boolean existsByURIAndTgChatId(URI uri, long tgChatId);

    void update(LinkModel link);
}
