package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
<<<<<<< HEAD
import ru.tinkoff.edu.java.bot.webclients.implementations.LinksClientImpl;
import ru.tinkoff.edu.java.bot.webclients.implementations.TGChatClientImpl;
import ru.tinkoff.edu.java.bot.webclients.interfaces.LinksClient;
import ru.tinkoff.edu.java.bot.webclients.interfaces.TGChatClient;
=======
import ru.tinkoff.edu.java.bot.webclients.implementations.ScrapperClientImpls;
import ru.tinkoff.edu.java.bot.webclients.interfaces.ScrapperClient;
>>>>>>> parent of 8b22849 (stage 2(and minor refactoring with app.configuration))

@Validated
@ConfigurationProperties(prefix = "client", ignoreUnknownFields = false)
public record ClientsConfig(String linksBaseUrl, String tgChatBaseUrl) {

    @Bean
    public LinksClient linksClient() {
        return new LinksClientImpl(linksBaseUrl);
    }

    @Bean
    public TGChatClient tgChatClient() {
        return new TGChatClientImpl(tgChatBaseUrl);
    }
}
