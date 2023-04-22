package ru.tinkoff.edu.java.bot.telegram.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.controllers.dto.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.telegram.MessageProcessor.UserManagerProcessor;
import ru.tinkoff.edu.java.bot.telegram.commands.Command;


import java.util.List;

@Component
public class TelegramBotImpl implements Bot {
    private final TelegramBot bot;

    private final UserManagerProcessor userManagerProcessor;

    @Autowired
    public TelegramBotImpl(@Value("${bot.token}") String token, UserManagerProcessor userManagerProcessor) {
        this.userManagerProcessor = userManagerProcessor;
        bot = new TelegramBot(token);

    }

    @PostConstruct
    public void start() {
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
            if (update.myChatMember() != null) {
                userManagerProcessor.unregisterChat(update);
                continue;
            }
            bot.execute(userManagerProcessor.process(update));
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @Override
    public void close() {
        bot.removeGetUpdatesListener();
    }

    public void sendUpdates(LinkUpdateRequest linkUpdateRequest) {
        for (var id : linkUpdateRequest.tgChatIds()) {
            bot.execute(new SendMessage(id, linkUpdateRequest.description()));
        }
    }

}
