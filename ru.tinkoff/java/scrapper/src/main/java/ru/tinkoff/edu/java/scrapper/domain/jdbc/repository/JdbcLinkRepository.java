package ru.tinkoff.edu.java.scrapper.domain.jdbc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
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


    public List<Link> readAll(long tgChatId) {
        return jdbcTemplate.query("select * from link where chat_id = ?", new Object[]{tgChatId}, linkMapper);
    }


    public URI delete(URI uri) {
        jdbcTemplate.update("delete from link where uri=?", uri.toString());
        return uri;
    }


    public Link add(Link model) {
        jdbcTemplate
                .update("insert into link (chat_id, uri, last_checked_at) values (?,?,?)",
                        model.tgChatId(),
                        model.uri().toString(),
                        model.lastCheckedAt()
                );
        return model;
    }

    public Link get(URI uri) {
        try {
            return jdbcTemplate
                    .queryForObject("select * from link where uri = ?", linkMapper, uri.toString());
        } catch (EmptyResultDataAccessException e) {
            throw new LinkNotFoundException("Link not found");
        }
    }

    public Boolean existsByURI(URI uri) {
        return jdbcTemplate
                .queryForObject("select exists(select 1 from link where uri = ?)", Boolean.class, uri.toString());
    }

}
