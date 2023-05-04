package ru.tinkoff.edu.repository.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.IntegrationEnvironment;
import ru.tinkoff.edu.java.domain.jdbc.mappers.TgChatMapper;
import ru.tinkoff.edu.java.domain.model.TgChatModel;
import ru.tinkoff.edu.java.domain.jdbc.repository.JdbcTgChatRepository;

import javax.sql.DataSource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class JdbcTgChatModelTest extends IntegrationEnvironment {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcTgChatRepository jdbcTgchatRepository;

    @Autowired
    private TgChatMapper tgChatMapper;

    private final List<Long> chatsId = List.of(123L, 567L);


    @BeforeEach
    public void up() {
        for (var chatId : chatsId) {
            jdbcTemplate.update("insert into chat(telegram_chat_id) values (?)",
                    chatId);
        }
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Find all operation test")
    public void findAllTest() {
        List<TgChatModel> allChats = jdbcTgchatRepository.readAll();
        assertThat(chatsId.size(), equalTo(allChats.size()));
        var i = 0;
        for (var chatId : chatsId) {
            assertThat(chatId, equalTo(allChats.get(i++).tgChatId()));
        }
    }


    @Transactional
    @Rollback
    @Test
    @DisplayName("Create operation test")
    public void addTest() {
        var newTgChatId = 89L;
        jdbcTgchatRepository.add(newTgChatId);
        List<TgChatModel> all = jdbcTemplate.query("select * from chat", tgChatMapper);
        assertThat(chatsId.size() + 1, equalTo(all.size()));
        assertThat(newTgChatId, equalTo(all.get(all.size() - 1).tgChatId()));
    }

    @Transactional
    @Rollback
    @Test
    @DisplayName("Delete operation test")
    public void removeTest() {
        jdbcTgchatRepository.delete(123L);
        assertThat(chatsId.size()-1,equalTo(jdbcTemplate
                .queryForObject("select count(*) from chat", Integer.class)));
        assertThat(chatsId.get(1),equalTo(jdbcTemplate
                .queryForObject("select telegram_chat_id from chat", Long.class)));

    }

    @TestConfiguration
    static class DbConfig {
        @Bean
        public DataSource dataSource() {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(POSTGRE_SQL_CONTAINER.getJdbcUrl());
            config.setUsername(POSTGRE_SQL_CONTAINER.getUsername());
            config.setPassword(POSTGRE_SQL_CONTAINER.getPassword());
            return new HikariDataSource(config);
        }

    }
}
