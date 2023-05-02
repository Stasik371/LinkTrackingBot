package ru.tinkoff.edu.java.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.bot.configuration.BotConfig;
import ru.tinkoff.edu.java.bot.configuration.ClientsConfig;

@SpringBootApplication
@EnableConfigurationProperties({BotConfig.class, ClientsConfig.class})
public class BotApplication {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(BotApplication.class, args);
    }

}
