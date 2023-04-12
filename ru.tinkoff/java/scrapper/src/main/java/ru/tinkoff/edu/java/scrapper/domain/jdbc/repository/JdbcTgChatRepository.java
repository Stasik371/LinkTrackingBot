package ru.tinkoff.edu.java.scrapper.domain.jdbc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.mappers.TgChatMapper;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.model.TgChat;
import ru.tinkoff.edu.java.scrapper.util.exceptions.ChatNotFoundException;

import java.net.URI;
import java.util.List;

@Repository
public class JdbcTgChatRepository {
    private JdbcTemplate jdbcTemplate;
    private TgChatMapper tgChatMapper;

    @Autowired
    public JdbcTgChatRepository(JdbcTemplate jdbcTemplate, TgChatMapper tgChatMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.tgChatMapper = tgChatMapper;
    }


    public List<TgChat> readAll() {
        return jdbcTemplate.query("select * from chat", tgChatMapper);
    }


    public boolean delete(long tgChatId) {
        return jdbcTemplate.update("delete from chat where telegram_chat_id = ?", tgChatId) > 0;
    }

    public boolean add(long tgChatId) {
        return jdbcTemplate
                .update("insert into chat(telegram_chat_id) values(?)",
                        tgChatId) > 0;
    }


    public Boolean existsById(long id) {
        return jdbcTemplate
                .queryForObject("select exists(select 1 from chat where telegram_chat_id = ?)", Boolean.class, id);
    }
}
