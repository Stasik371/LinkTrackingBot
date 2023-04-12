package ru.tinkoff.edu.java.scrapper.domain.jdbc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.mappers.LinkMapper;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.model.Link;

import java.net.URI;
import java.util.List;


@Repository
public class JdbcLinkRepositoryImpl {

    private final JdbcTemplate jdbcTemplate;
    private final LinkMapper linkMapper;

    @Autowired
    public JdbcLinkRepositoryImpl(JdbcTemplate jdbcTemplate, LinkMapper linkMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.linkMapper = linkMapper;
    }


    public List<Link> readAll() {
        return jdbcTemplate.query("select * from link", linkMapper);
    }


    public boolean delete(URI uri) {
        return jdbcTemplate.update("delete from link where uri=?", uri.toString()) > 0;
    }


    public boolean add(Link model) {
        return jdbcTemplate
                .update("insert into link (chat_id, uri, last_checked_at) values (?,?,?)",
                        model.tgChatId(),
                        model.uri().toString(),
                        model.lastCheckedAt()
                ) > 0;
    }
}
