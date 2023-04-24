package ru.tinkoff.edu.java.domain.jdbc.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.domain.model.TgChatModel;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TgChatMapper implements RowMapper<TgChatModel> {
    @Override
    public TgChatModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TgChatModel(rs.getLong("chat_id_pk"),
                rs.getLong("telegram_chat_id"));
    }
}
