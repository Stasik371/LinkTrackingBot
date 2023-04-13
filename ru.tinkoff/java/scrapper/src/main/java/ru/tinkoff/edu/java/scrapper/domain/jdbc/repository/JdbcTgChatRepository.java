package ru.tinkoff.edu.java.scrapper.domain.jdbc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.mappers.TgChatMapper;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.model.TgChat;


import java.util.List;

@Repository
public class JdbcTgChatRepository {
    private final JdbcTemplate jdbcTemplate;
    private final TgChatMapper tgChatMapper;

    @Autowired
    public JdbcTgChatRepository(JdbcTemplate jdbcTemplate, TgChatMapper tgChatMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.tgChatMapper = tgChatMapper;
    }


    public List<TgChat> readAll() {
        return jdbcTemplate.query("select * from chat", tgChatMapper);
    }

    @Transactional
    public void delete(long tgChatId) {
        jdbcTemplate.update("delete from chat where telegram_chat_id = ?", tgChatId);
    }

    @Transactional
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
