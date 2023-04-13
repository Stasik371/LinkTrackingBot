package ru.tinkoff.edu.java.scrapper.domain.jdbc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.mappers.LinkMapper;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.model.Link;
import ru.tinkoff.edu.java.scrapper.util.exceptions.LinkNotFoundException;

import java.net.URI;
import java.util.List;


@Repository
public class JdbcLinkRepository {

    private final JdbcTemplate jdbcTemplate;
    private final LinkMapper linkMapper;

    @Autowired
    public JdbcLinkRepository(JdbcTemplate jdbcTemplate, LinkMapper linkMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.linkMapper = linkMapper;
    }

    @Transactional
    public List<Link> readAll(long tgChatId) {
        return jdbcTemplate.query("select * from link where chat_id = ?", new Object[]{tgChatId}, linkMapper);
    }

    @Transactional
    public URI delete(URI uri, long tgChatId) {
        jdbcTemplate.update("delete from link where uri=? and chat_id = ?", uri.toString(), tgChatId);
        return uri;
    }

    @Transactional
    public Link add(Link model) {
        jdbcTemplate
                .update("insert into link (chat_id, uri, last_checked_at) values (?,?,?)",
                        model.tgChatId(),
                        model.uri().toString(),
                        model.lastCheckedAt()
                );
        return model;
    }

    @Transactional
    public Boolean existsByURIAndTgChatId(URI uri, long tgChatId) {
        return jdbcTemplate
                .queryForObject("select exists(select 1 from link where uri = ? and chat_id=?)", Boolean.class, uri.toString(), tgChatId);
    }

}
