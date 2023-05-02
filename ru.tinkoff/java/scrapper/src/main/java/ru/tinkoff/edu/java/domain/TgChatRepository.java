package ru.tinkoff.edu.java.domain;

import ru.tinkoff.edu.java.domain.model.TgChatModel;

import java.net.URI;
import java.util.List;

public interface TgChatRepository {
   List<TgChatModel> readAll();

    void delete(long tgChatId);

    void add(long tgChatId);

    Boolean existsById(long id);

    List<TgChatModel> readAllByURI(URI uri);
}
