package ru.tinkoff.edu.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.IntegrationEnvironment;

import ru.tinkoff.edu.java.domain.jdbc.mappers.LinkMapper;
import ru.tinkoff.edu.java.domain.model.LinkModel;
import ru.tinkoff.edu.java.domain.jdbc.repository.JdbcLinkRepository;


import javax.sql.DataSource;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class JdbcLinkTestModel extends IntegrationEnvironment {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcLinkRepository jdbcLinkRepository;

    @Autowired
    private LinkMapper linkMapper;

    private final int tgChatId = (int) (Math.random() * 100);


    private final List<String> urls = List.of("https://github.com/Stasik371/TinkoffBot",
            "https://github.com/Stasik371/BookingApplication");


    @BeforeEach
    public void createRecords() {
        jdbcTemplate.update("insert into chat(telegram_chat_id) values (?)", tgChatId);
        for (var url : urls) {
            jdbcTemplate.update("insert into link(chat_id, uri, last_checked_at) values (?, ?, ?)",
                    tgChatId, url, OffsetDateTime.now());
        }
    }


    @Test
    @Transactional
    @Rollback
    @DisplayName("Find all operation test")
    public void findAllTest() {
        List<LinkModel> allLinks = jdbcLinkRepository.readAllWithTgChatId(tgChatId);
        assertThat(urls.size(), equalTo(allLinks.size()));
        var i = 0;
        for (var url : urls) {
            assertThat(url, equalTo(allLinks.get(i++).uri().toString()));
        }
    }


    @Transactional
    @Rollback
    @Test
    @DisplayName("Create operation test")
    public void addTest() {
        var newUrl = URI.create("https://github.com/Stasik371/VkCoursesHW2");
        LinkModel link = new LinkModel(1, newUrl);
        jdbcLinkRepository.add(link);
        List<LinkModel> all = jdbcTemplate.query("select * from link", linkMapper);
        assertThat(urls.size() + 1, equalTo(all.size()));
        assertThat(newUrl, equalTo(all.get(all.size() - 1).uri()));
    }

    @Transactional
    @Rollback
    @Test
    @DisplayName("Delete operation test")
    public void removeTest() {
        jdbcLinkRepository.delete(URI.create("https://github.com/Stasik371/TinkoffBot"), 1);
        assertThat(urls.size() - 1, equalTo(jdbcTemplate.queryForObject("select count(*) from link", Integer.class)));
        assertThat(urls.get(1), equalTo(jdbcTemplate.queryForObject("select uri from link", String.class)));
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
