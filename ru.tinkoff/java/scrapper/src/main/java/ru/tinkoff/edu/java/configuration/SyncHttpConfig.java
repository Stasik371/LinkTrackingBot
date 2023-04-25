package ru.tinkoff.edu.java.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.services.implementations.updatesenders.HttpLinkUpdateSender;
import ru.tinkoff.edu.java.services.implementations.updatesenders.UpdateSender;
import ru.tinkoff.edu.java.webclients.internal.sync.implementations.BotClientBaseImpl;


@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "false")
@Configuration
public class SyncHttpConfig {
    @Value("${app.bot-base-url}")
    private String botBaseUrl;

    @Bean
    public @NotNull UpdateSender httpLinkUpdateSender() {
        return new HttpLinkUpdateSender(new BotClientBaseImpl(botBaseUrl));
    }
}
