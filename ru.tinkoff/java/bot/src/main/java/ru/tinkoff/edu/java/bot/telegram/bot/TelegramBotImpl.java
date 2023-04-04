package ru.tinkoff.edu.java.bot.telegram.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SetMyCommands;
import ru.tinkoff.edu.java.bot.telegram.MessageProcessor.UserManagerProcessor;
import ru.tinkoff.edu.java.bot.telegram.MessageProcessor.UserManagerProcessorImpl;
import ru.tinkoff.edu.java.bot.telegram.commands.Command;


import java.util.List;


public class TelegramBotImpl implements Bot {
    private TelegramBot bot;

    private UserManagerProcessor userManagerProcessor;


    public TelegramBotImpl(String token, UserManagerProcessor userManagerProcessor) {
        this.userManagerProcessor = userManagerProcessor;
        bot = new TelegramBot(token);
        bot.setUpdatesListener(this);
        bot.execute(new SetMyCommands(userManagerProcessor
                .commands()
                .stream()
                .map(Command::toApiCommand)
                .toArray(BotCommand[]::new)));
    }

    @Override
    public int process(List<Update> updates) {
        for (var update : updates) {
            bot.execute(userManagerProcessor.process(update));
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @Override
    public void close() {
        bot.removeGetUpdatesListener();
    }

}
