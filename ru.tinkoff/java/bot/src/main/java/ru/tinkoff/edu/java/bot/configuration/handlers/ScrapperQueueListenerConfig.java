package ru.tinkoff.edu.java.bot.configuration.handlers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.telegram.bot.Bot;

import ru.tinkoff.edu.java.bot.update.senders.ScrapperQueueListener;
import ru.tinkoff.edu.java.bot.update.senders.UpdateReciever;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "true")
public class ScrapperQueueListenerConfig {

    @Bean
    public UpdateReciever scrapperQueueListener(Bot bot) {
        return new ScrapperQueueListener(bot);
    }
}
