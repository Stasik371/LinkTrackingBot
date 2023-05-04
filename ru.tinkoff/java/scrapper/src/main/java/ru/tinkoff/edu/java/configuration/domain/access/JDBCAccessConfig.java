package ru.tinkoff.edu.java.configuration.domain.access;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.domain.LinkRepository;
import ru.tinkoff.edu.java.domain.TgChatRepository;
import ru.tinkoff.edu.java.domain.jdbc.mappers.LinkMapper;
import ru.tinkoff.edu.java.domain.jdbc.mappers.TgChatMapper;
import ru.tinkoff.edu.java.domain.jdbc.repository.JdbcLinkRepository;
import ru.tinkoff.edu.java.domain.jdbc.repository.JdbcTgChatRepository;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class JDBCAccessConfig {

    @Bean
    public LinkRepository linkRepository(JdbcTemplate jdbcTemplate, LinkMapper linkMapper) {
        return new JdbcLinkRepository(jdbcTemplate, linkMapper);
    }

    @Bean
    public TgChatRepository tgChatRepository(JdbcTemplate jdbcTemplate, TgChatMapper tgChatMapper) {
        return new JdbcTgChatRepository(jdbcTemplate, tgChatMapper);
    }
}