package ru.tinkoff.edu.java.scrapper.domain.jdbc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.mappers.TgChatMapper;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.model.TgChat;

import java.util.List;

@Repository
public class JdbcTgChatJdbcRepositoryImpl {
    private JdbcTemplate jdbcTemplate;
    private TgChatMapper tgChatMapper;

    @Autowired
    public JdbcTgChatJdbcRepositoryImpl(JdbcTemplate jdbcTemplate, TgChatMapper tgChatMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.tgChatMapper = tgChatMapper;
    }


    public List<TgChat> readAll() {
        return jdbcTemplate.query("select * from chat", tgChatMapper);
    }


    public boolean delete(long tgChatId) {
        return jdbcTemplate.update("delete from chat where telegram_chat_id = ?", tgChatId) > 0;
    }

    public boolean add(TgChat model) {
        return jdbcTemplate
                .update("insert into chat(telegram_chat_id) values(?)",
                        model.tgChatId()) > 0;
    }
}
