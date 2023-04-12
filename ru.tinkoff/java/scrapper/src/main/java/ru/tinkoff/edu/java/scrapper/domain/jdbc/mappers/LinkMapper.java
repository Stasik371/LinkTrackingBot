package ru.tinkoff.edu.java.scrapper.domain.jdbc.mappers;

import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.model.Link;


import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

@Component
public class LinkMapper implements RowMapper<Link> {

    @Override
    public Link mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return new Link(rs.getLong("link_id_pk"),
                rs.getLong("chat_id"),
                URI.create(rs.getString("uri")),
                rs.getObject("last_checked_at", OffsetDateTime.class));
    }
}
