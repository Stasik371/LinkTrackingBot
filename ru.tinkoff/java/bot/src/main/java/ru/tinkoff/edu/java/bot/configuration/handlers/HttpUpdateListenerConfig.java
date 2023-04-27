package ru.tinkoff.edu.java.bot.configuration.handlers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.telegram.bot.Bot;
import ru.tinkoff.edu.java.bot.update.senders.HttpUpdateListener;
import ru.tinkoff.edu.java.bot.update.senders.UpdateSender;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "false")
public class HttpUpdateListenerConfig {
    @Bean
    public UpdateSender httpUpdateListener(Bot bot){
        return new HttpUpdateListener(bot);
    }
}
