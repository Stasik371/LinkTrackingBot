package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.telegram.MessageProcessor.UserManagerProcessor;
import ru.tinkoff.edu.java.bot.telegram.MessageProcessor.UserManagerProcessorImpl;
import ru.tinkoff.edu.java.bot.telegram.commands.*;
import ru.tinkoff.edu.java.bot.webclients.interfaces.ScrapperClient;

@Configuration
public class UserMessageProcessorConfig {
    @Bean
    @Autowired
    public UserManagerProcessor userMessageProcessor(ScrapperClient scrapperClient, StartCommand startCommand,
                                                     HelpCommand helpCommand, TrackCommand trackCommand,
                                                     UntrackCommand untrackCommand, ListCommand listCommand) {
        return new UserManagerProcessorImpl(scrapperClient, startCommand, helpCommand, trackCommand, untrackCommand, listCommand);
    }
}
