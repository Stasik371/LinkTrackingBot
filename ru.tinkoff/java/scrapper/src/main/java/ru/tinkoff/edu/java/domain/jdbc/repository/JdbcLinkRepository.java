package ru.tinkoff.edu.java.domain.jdbc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.domain.LinkRepository;
import ru.tinkoff.edu.java.domain.jdbc.mappers.LinkMapper;
import ru.tinkoff.edu.java.domain.model.LinkModel;
import ru.tinkoff.edu.java.domain.model.TgChatModel;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;


@Repository
public class JdbcLinkRepository implements LinkRepository {

    @Value("${minutesToCheck}")
    private int minutesToCheck;

    private final JdbcTemplate jdbcTemplate;
    private final LinkMapper linkMapper;

    @Autowired
    public JdbcLinkRepository(JdbcTemplate jdbcTemplate, LinkMapper linkMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.linkMapper = linkMapper;
    }

    @Transactional
    @Override
    public List<LinkModel> readAllToUpdate() {
        return readAll()
                .stream()
                .filter(l -> l
                        .lastCheckedAt()
                        .isBefore(OffsetDateTime.of(LocalDateTime.now().minusMinutes(minutesToCheck), ZoneOffset.UTC)))
                .toList();
    }

    @Transactional
    @Override
    public List<LinkModel> readAllWithTgChatId(long tgChatId) {
        return jdbcTemplate.query("select * from link where chat_id = ?", new Object[]{tgChatId}, linkMapper);
    }

    @Transactional
    @Override
    public List<LinkModel> readAll() {
        return jdbcTemplate.query("select * from link", linkMapper);
    }

    @Transactional
    @Override
    public URI delete(URI uri, long tgChatId) {
        jdbcTemplate.update("delete from link where uri=? and chat_id = ?", uri.toString(), tgChatId);
        return uri;
    }


    @Override
    public LinkModel add(LinkModel model) {
        jdbcTemplate
                .update("insert into link (chat_id, uri, last_checked_at, last_activity_at, issue_count, answer_count) values (?, ?, ?, ?, ?, ?)",
                        model.tgChatId(),
                        model.uri().toString(),
                        model.lastCheckedAt(),
                        model.lastActivity(),
                        model.issueCount(),
                        model.answerCount()
                );
        return model;
    }

    @Transactional
    @Override
    public Boolean existsByURIAndTgChatId(URI uri, long tgChatId) {
        return jdbcTemplate
                .queryForObject("select exists(select 1 from link where uri = ? and chat_id=?)", Boolean.class, uri.toString(), tgChatId);
    }


    @Override
    public void update(LinkModel link) {
        jdbcTemplate.update("update link set last_checked_at=?, last_activity_at=?, " +
                        "issue_count=?, answer_count=? where link_id_pk = ?",
                link.lastCheckedAt(), link.lastActivity(), link.issueCount(), link.answerCount(), link.id());
    }

    @Transactional
    @Override
    public List<TgChatModel> readAllByURI(URI uri) {
        return jdbcTemplate.query("select * from link where uri = ?", new Object[]{uri.toString()}, linkMapper)
                .stream()
                .map(l -> new TgChatModel(l.tgChatId()))
                .toList();
    }

}
