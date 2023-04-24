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
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.domain.jpa.repository.JPALinkRepository;
import ru.tinkoff.edu.java.domain.jpa.repository.JPATgChatRepository;
import ru.tinkoff.edu.java.domain.model.LinkModel;

import javax.sql.DataSource;
import java.net.URI;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import ru.tinkoff.edu.repository.IntegrationEnvironment;

@SpringBootTest
public class JpaLinkTest extends IntegrationEnvironment {

    @Autowired
    private JPALinkRepository jpaLinkRepository;

    @Autowired
    private JPATgChatRepository jpaTgChatRepository;


    private final int tgChatId = (int) (Math.random() * 100);


    private final List<String> urls = List.of("https://github.com/Stasik371/TinkoffBot",
            "https://github.com/Stasik371/BookingApplication");


    @BeforeEach
    public void createRecords() {
        jpaTgChatRepository.add(tgChatId);

        for (var url : urls) {
            jpaLinkRepository.add(new LinkModel(tgChatId, URI.create(url)));
        }
    }

    @Test
    @DisplayName("Find all operation test")
    @Transactional
    @Rollback
    public void findAllTest() {
        assertThat(urls.size(), equalTo(jpaLinkRepository.readAll().size()));
        for (int i = 0; i < urls.size(); i++) {
            assertThat(jpaLinkRepository.readAll().get(i).uri().toString(), equalTo(urls.get(i)));
        }
    }

    @Transactional
    @Rollback
    @Test
    @DisplayName("Create operation test")
    public void addTest() {
        var newUrl = "https://github.com/Stasik371/testWorkIPV6";
        jpaLinkRepository.add(new LinkModel(tgChatId,URI.create(newUrl)));
        List<LinkModel> all = jpaLinkRepository.readAll();
        assertThat(urls.size() + 1, equalTo(all.size()));
        assertThat(newUrl, equalTo(all.get(all.size() - 1).uri().toString()));
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

