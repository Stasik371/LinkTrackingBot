package ru.tinkoff.edu.repository.jpa;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.IntegrationEnvironment;
import ru.tinkoff.edu.java.ScrapperApplication;
import ru.tinkoff.edu.java.domain.jpa.repository.JPATgChatRepository;
import ru.tinkoff.edu.java.domain.model.TgChatModel;


import javax.sql.DataSource;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@TestPropertySource(properties = "app.database-access-type=jpa")
@SpringBootTest(classes = ScrapperApplication.class)
public class JpaTgChatTest extends IntegrationEnvironment {
    @Autowired
    private JPATgChatRepository tgChatRepository;


    private final List<Long> tgChatIds = List.of(123L, 456L, 789L);

    @BeforeEach
    public void setupChats() {
        tgChatIds.forEach(tg -> tgChatRepository.add(tg));
    }

    @Test
    @DisplayName("Find all operation test")
    @Transactional
    @Rollback
    public void findAllTest() {
        assertThat(tgChatIds.size(), equalTo(tgChatRepository.readAll().size()));
        for (int i = 0; i < tgChatIds.size(); i++) {
            assertThat(tgChatRepository.readAll().get(i).tgChatId(), equalTo(tgChatIds.get(i)));
        }
    }

    @Transactional
    @Rollback
    @Test
    @DisplayName("Create operation test")
    public void addTest() {
        var newTgChatId = 89L;
        tgChatRepository.add(newTgChatId);
        List<TgChatModel> all = tgChatRepository.readAll();
        assertThat(tgChatIds.size() + 1, equalTo(all.size()));
        assertThat(newTgChatId, equalTo(all.get(all.size() - 1).tgChatId()));
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
