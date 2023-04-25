package ru.tinkoff.edu.java.domain.jooq.repository;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.tinkoff.edu.java.domain.LinkRepository;
import ru.tinkoff.edu.java.domain.jooq.generated.tables.Link;
import ru.tinkoff.edu.java.domain.model.LinkModel;
import ru.tinkoff.edu.java.domain.model.TgChatModel;

import java.net.URI;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.List;




public class JooqLinkRepository implements LinkRepository {

    private final DSLContext context;
    @Value("${app.minutes-to-check}")
    private int minutesToCheck;

    @Autowired
    public JooqLinkRepository(DSLContext context) {
        this.context = context;
    }


    private final Link link = Link.LINK;

    @Override
    public List<LinkModel> readAllToUpdate() {
        var cutoffTime = new Timestamp(System.currentTimeMillis() - (long) minutesToCheck * 60 * 1000);
        return context
                .select(link)
                .where(link.LAST_CHECKED_AT
                        .le(cutoffTime.toLocalDateTime()))
                .fetch()
                .stream()
                .map(record -> new LinkModel(
                        record.getValue(link.LINK_ID_PK),
                        record.getValue(link.CHAT_ID),
                        URI.create(record.getValue(link.URI)),
                        record.getValue(link.LAST_CHECKED_AT)
                                .atOffset(ZoneOffset.UTC),
                        record.getValue(link.LAST_ACTIVITY_AT)
                                .atOffset(ZoneOffset.UTC),
                        record.getValue(link.ISSUE_COUNT),
                        record.getValue(link.ANSWER_COUNT)))
                .toList();
    }

    @Override
    public List<LinkModel> readAllWithTgChatId(long tgChatId) {
        return context
                .select()
                .from(link)
                .where(link.CHAT_ID.eq(tgChatId))
                .fetch()
                .stream()
                .map(record -> new LinkModel(
                        record.getValue(link.LINK_ID_PK),
                        record.getValue(link.CHAT_ID),
                        URI.create(record.getValue(link.URI)),
                        record.getValue(link.LAST_CHECKED_AT)
                                .atOffset(ZoneOffset.UTC),
                        record.getValue(link.LAST_ACTIVITY_AT)
                                .atOffset(ZoneOffset.UTC),
                        record.getValue(link.ISSUE_COUNT),
                        record.getValue(link.ANSWER_COUNT)))
                .toList();
    }

    @Override
    public List<LinkModel> readAll() {
        return context
                .select()
                .from(link)
                .fetch()
                .stream()
                .map(record -> new LinkModel(
                        record.getValue(link.LINK_ID_PK),
                        record.getValue(link.CHAT_ID),
                        URI.create(record.getValue(link.URI)),
                        record.getValue(link.LAST_CHECKED_AT)
                                .atOffset(ZoneOffset.UTC),
                        record.getValue(link.LAST_ACTIVITY_AT)
                                .atOffset(ZoneOffset.UTC),
                        record.getValue(link.ISSUE_COUNT),
                        record.getValue(link.ANSWER_COUNT)))
                .toList();
    }

    @Override
    public URI delete(URI uri, long tgChatId) {
        context
                .deleteFrom(link)
                .where(link.URI.eq(uri.toString()))
                .and(link.CHAT_ID.eq(tgChatId))
                .execute();
        return uri;
    }

    @Override
    public LinkModel add(LinkModel model) {
        context.insertInto(link, link.CHAT_ID, link.URI,
                        link.LAST_CHECKED_AT, link.LAST_ACTIVITY_AT,
                        link.ISSUE_COUNT, link.ANSWER_COUNT)
                .values(model.tgChatId(), model.uri().toString(),
                        model.lastCheckedAt().toLocalDateTime(),
                        model.lastActivity().toLocalDateTime(),
                        model.issueCount(), model.answerCount())
                .execute();
        return model;
    }

    @Override
    public Boolean existsByURIAndTgChatId(URI uri, long tgChatId) {
        return context
                .fetchExists(context
                        .selectFrom(link)
                        .where(link.URI.eq(uri.toString()))
                        .and(link.CHAT_ID.eq(tgChatId)));
    }

    @Override
    public void update(LinkModel link) {
        context.update(this.link)
                .set(this.link.CHAT_ID, link.tgChatId())
                .set(this.link.URI, link.uri().toString())
                .set(this.link.LAST_CHECKED_AT, link.lastCheckedAt().toLocalDateTime())
                .set(this.link.LAST_ACTIVITY_AT, link.lastActivity().toLocalDateTime())
                .set(this.link.ISSUE_COUNT, link.issueCount())
                .set(this.link.ANSWER_COUNT, link.answerCount())
                .where(this.link.LINK_ID_PK.eq(link.id()))
                .execute();
    }


    public List<TgChatModel> readAllByURI(URI uri) {
        return context
                .select()
                .from(link)
                .where(link.URI.eq(uri.toString()))
                .stream()
                .map(l -> new TgChatModel(l.getValue(link.CHAT_ID)))
                .toList();
    }
}
