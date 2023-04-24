package ru.tinkoff.edu.java.domain.model;

public record TgChatModel(long id, long tgChatId) {
    public TgChatModel(long tgChatId) {
        this(-1, tgChatId);
    }

}
