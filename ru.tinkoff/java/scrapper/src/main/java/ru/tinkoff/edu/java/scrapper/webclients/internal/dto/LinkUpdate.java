package ru.tinkoff.edu.java.scrapper.webclients.internal.dto;

public record LinkUpdate(long id, String url, String description, long[] tgChatIds) {
}
