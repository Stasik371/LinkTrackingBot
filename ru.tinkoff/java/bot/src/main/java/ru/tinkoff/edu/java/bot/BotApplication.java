package ru.tinkoff.edu.java.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.bot.configuration.BotConfig;
import ru.tinkoff.edu.java.bot.configuration.ClientsConfig;
<<<<<<< HEAD
=======
import ru.tinkoff.edu.java.bot.webclients.implementations.ScrapperClientImpls;
>>>>>>> parent of 8b22849 (stage 2(and minor refactoring with app.configuration))

@SpringBootApplication
@EnableConfigurationProperties({BotConfig.class, ClientsConfig.class})
public class BotApplication {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(BotApplication.class, args);
<<<<<<< HEAD
=======
        var client = ctx.getBean(ScrapperClientImpls.class);
        System.out.println(client.getAllLinks(1));
>>>>>>> parent of 8b22849 (stage 2(and minor refactoring with app.configuration))
    }

}
