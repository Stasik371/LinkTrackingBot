package ru.tinkoff.edu.java.bot.controllers.dto;

import org.hibernate.validator.constraints.URL;

public record LinkUpdateRequest(long id, @URL String url, String description, long[] tgChatIds) {
}
