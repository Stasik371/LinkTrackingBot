package ru.tinkoff.edu.java.domain.jdbc.mappers;

import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.domain.model.LinkModel;


import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

@Component
public class LinkMapper implements RowMapper<LinkModel> {

    @Override
    public LinkModel mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return new LinkModel(rs.getLong("link_id_pk"),
                rs.getLong("chat_id"),
                URI.create(rs.getString("uri")),
                rs.getObject("last_checked_at", OffsetDateTime.class),
                rs.getObject("last_activity_at", OffsetDateTime.class),
                rs.getInt("issue_count"),
                rs.getInt("answer_count"));
    }
}
