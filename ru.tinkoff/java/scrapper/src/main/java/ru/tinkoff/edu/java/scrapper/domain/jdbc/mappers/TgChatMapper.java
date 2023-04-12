package ru.tinkoff.edu.java.scrapper.domain.jdbc.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.model.Link;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.model.TgChat;

import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

@Component
public class TgChatMapper implements RowMapper<TgChat> {
    @Override
    public TgChat mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TgChat(rs.getLong("chat_id_pk"),
                rs.getLong("telegram_chat_id"));
    }
}
