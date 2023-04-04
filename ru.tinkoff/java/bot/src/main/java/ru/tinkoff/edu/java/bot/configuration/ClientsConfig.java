package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.java.bot.webclients.implementations.LinksClientImpl;
import ru.tinkoff.edu.java.bot.webclients.implementations.TGChatClientImpl;
import ru.tinkoff.edu.java.bot.webclients.interfaces.LinksClient;
import ru.tinkoff.edu.java.bot.webclients.interfaces.TGChatClient;


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
