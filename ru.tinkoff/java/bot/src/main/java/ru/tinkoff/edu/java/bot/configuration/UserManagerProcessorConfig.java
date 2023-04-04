package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.telegram.MessageProcessor.UserManagerProcessor;
import ru.tinkoff.edu.java.bot.telegram.MessageProcessor.UserManagerProcessorImpl;
import ru.tinkoff.edu.java.bot.telegram.commands.*;

@Configuration
public class UserManagerProcessorConfig {

    @Bean
    public UserManagerProcessor userManagerProcessor(HelpCommand helpCommand, ListCommand listCommand,
                                                     StartCommand startCommand, TrackCommand trackCommand,
                                                     UntrackCommand untrackCommand) {
        return new UserManagerProcessorImpl(helpCommand, listCommand, startCommand, trackCommand, untrackCommand);
    }
}
