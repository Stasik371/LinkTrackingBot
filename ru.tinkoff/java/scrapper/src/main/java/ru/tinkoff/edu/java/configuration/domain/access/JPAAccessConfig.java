package ru.tinkoff.edu.java.configuration.domain.access;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.domain.LinkRepository;
import ru.tinkoff.edu.java.domain.TgChatRepository;
import ru.tinkoff.edu.java.domain.jpa.generatedRepository.LinkEntityJPARepository;
import ru.tinkoff.edu.java.domain.jpa.generatedRepository.TgChatEntityJPARepository;
import ru.tinkoff.edu.java.domain.jpa.repository.JPALinkRepository;
import ru.tinkoff.edu.java.domain.jpa.repository.JPATgChatRepository;


@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JPAAccessConfig {
    @Bean
    public LinkRepository linkRepository(LinkEntityJPARepository jpaRepository) {
        return new JPALinkRepository(jpaRepository);
    }

    @Bean
    public TgChatRepository tgChatRepository(TgChatEntityJPARepository jpaRepository) {
        return new JPATgChatRepository(jpaRepository);
    }
}