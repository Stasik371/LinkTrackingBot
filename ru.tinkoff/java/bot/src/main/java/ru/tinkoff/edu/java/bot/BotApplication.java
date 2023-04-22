package ru.tinkoff.edu.java.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.bot.configuration.ClientsConfig;
import ru.tinkoff.edu.java.bot.webclients.implementations.ScrapperClientImpls;

@SpringBootApplication
@EnableConfigurationProperties({ClientsConfig.class})
public class BotApplication {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(BotApplication.class, args);
        var client = ctx.getBean(ScrapperClientImpls.class);
        System.out.println(client.getAllLinks(1));
    }

}
