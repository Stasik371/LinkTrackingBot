package ru.tinkoff.edu.java.configuration.domain.access;

import org.jooq.DSLContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.domain.LinkRepository;
import ru.tinkoff.edu.java.domain.TgChatRepository;
import ru.tinkoff.edu.java.domain.jooq.repository.JooqLinkRepository;
import ru.tinkoff.edu.java.domain.jooq.repository.JooqTgChatRepository;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jooq")
public class JOOQAccessConfig {

    @Bean
    public LinkRepository linkRepository(DSLContext dslcontext) {
        return new JooqLinkRepository(dslcontext);
    }

    @Bean
    public TgChatRepository tgChatRepository(DSLContext dslContext) {
        return new JooqTgChatRepository(dslContext);
    }
}
