package ru.tinkoff.edu.java.bot.configuration;


import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.java.bot.telegram.MessageProcessor.UserManagerProcessor;
import ru.tinkoff.edu.java.bot.telegram.bot.Bot;
import ru.tinkoff.edu.java.bot.telegram.bot.TelegramBotImpl;



@ConfigurationProperties(prefix = "bot", ignoreUnknownFields = false)
public class BotConfig {
    @Setter
    private String token;
    private UserManagerProcessor userManagerProcessor;

    @Autowired
    public BotConfig(UserManagerProcessor userManagerProcessor) {
        this.userManagerProcessor = userManagerProcessor;
    }

    @Bean
    public Bot telegramBot() {
        return new TelegramBotImpl(token, userManagerProcessor);
    }
}
