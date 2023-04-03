package ru.tinkoff.edu.java.domain.model;


import java.net.URI;
import java.time.OffsetDateTime;


public record LinkModel(long id, long tgChatId, URI uri, OffsetDateTime lastCheckedAt, OffsetDateTime lastActivity,
                        int issueCount, int answerCount) {
    public LinkModel(long tgChatId, URI uri) {
        this(-1, tgChatId, uri, OffsetDateTime.now(), OffsetDateTime.now(), 0, 0);
    }

    public LinkModel(long tgChatId, URI uri, OffsetDateTime lastActivity, int issueCount, int answerCount) {
        this(-1, tgChatId, uri, OffsetDateTime.now(), lastActivity, issueCount, answerCount);
    }


}
