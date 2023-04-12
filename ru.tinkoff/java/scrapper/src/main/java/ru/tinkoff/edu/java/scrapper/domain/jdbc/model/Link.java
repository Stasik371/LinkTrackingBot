package ru.tinkoff.edu.java.scrapper.domain.jdbc.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.time.OffsetDateTime;


public record Link(long id, long tgChatId, URI uri, OffsetDateTime lastCheckedAt) {
    public Link(long tgChatId, URI uri, OffsetDateTime lastCheckedAt) {
        this(-1, tgChatId, uri, lastCheckedAt);
    }
}
