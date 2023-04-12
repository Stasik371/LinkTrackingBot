package ru.tinkoff.edu.java.scrapper.domain.jdbc.model;

public record TgChat(long id, long tgChatId) {
    public TgChat(long tgChatId) {
        this(-1, tgChatId);
    }

}
