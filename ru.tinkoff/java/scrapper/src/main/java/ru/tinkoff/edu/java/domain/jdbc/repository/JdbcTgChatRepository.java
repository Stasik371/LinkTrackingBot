package ru.tinkoff.edu.java.domain.jdbc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.domain.TgChatRepository;
import ru.tinkoff.edu.java.domain.jdbc.mappers.TgChatMapper;
import ru.tinkoff.edu.java.domain.model.TgChatModel;


import java.util.List;


public class JdbcTgChatRepository implements TgChatRepository {
    private final JdbcTemplate jdbcTemplate;
    private final TgChatMapper tgChatMapper;

    @Autowired
    public JdbcTgChatRepository(JdbcTemplate jdbcTemplate, TgChatMapper tgChatMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.tgChatMapper = tgChatMapper;
    }


    public List<TgChatModel> readAll() {
        return jdbcTemplate.query("select * from chat", tgChatMapper);
    }


    public void delete(long tgChatId) {
        jdbcTemplate.update("delete from chat where telegram_chat_id = ?", tgChatId);
    }


    public void add(long tgChatId) {
        jdbcTemplate.update("insert into chat(telegram_chat_id) values(?)",
                tgChatId);
    }


    public Boolean existsById(long id) {
        return jdbcTemplate
                .queryForObject("select exists(select 1 from chat where telegram_chat_id = ?)", Boolean.class, id);
    }


}
