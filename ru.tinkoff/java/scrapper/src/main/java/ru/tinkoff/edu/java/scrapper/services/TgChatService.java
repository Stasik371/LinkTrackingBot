package ru.tinkoff.edu.java.scrapper.services;

public interface TgChatService {
    void register(long tgChatId);

    void unregister(long tgChatId);
}
