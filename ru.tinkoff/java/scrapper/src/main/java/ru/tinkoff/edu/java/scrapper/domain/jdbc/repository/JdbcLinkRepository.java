package ru.tinkoff.edu.java.scrapper.domain.jdbc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.mappers.LinkMapper;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.model.Link;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;


@Repository
public class JdbcLinkRepository {

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
    public List<Link> readAllToUpdate() {
        return readAll()
                .stream()
                .filter(l -> l
                        .lastCheckedAt()
                        .isBefore(OffsetDateTime.of(LocalDateTime.now().minusMinutes(minutesToCheck), ZoneOffset.UTC)))
                .toList();
    }

    @Transactional
    public List<Link> readAllWithTgChatId(long tgChatId) {
        return jdbcTemplate.query("select * from link where chat_id = ?", new Object[]{tgChatId}, linkMapper);
    }

    @Transactional
    public List<Link> readAll() {
        return jdbcTemplate.query("select * from link", linkMapper);
    }

    @Transactional
    public URI delete(URI uri, long tgChatId) {
        jdbcTemplate.update("delete from link where uri=? and chat_id = ?", uri.toString(), tgChatId);
        return uri;
    }

    @Transactional
    public Link add(Link model) {
        jdbcTemplate
                .update("insert into link (chat_id, uri, last_checked_at, last_pushed_at, issue_count, answer_count) values (?, ?, ?, ?, ?, ?)",
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
    public Boolean existsByURIAndTgChatId(URI uri, long tgChatId) {
        return jdbcTemplate
                .queryForObject("select exists(select 1 from link where uri = ? and chat_id=?)", Boolean.class, uri.toString(), tgChatId);
    }

    @Transactional
    public void update(Link link) {
        jdbcTemplate.update("update link set last_checked_at=?, last_pushed_at=?, " +
                        "issue_count=?, answer_count=? where link_id_pk = ?",
                link.lastCheckedAt(), link.lastActivity(), link.issueCount(), link.answerCount(), link.id());
    }

}
