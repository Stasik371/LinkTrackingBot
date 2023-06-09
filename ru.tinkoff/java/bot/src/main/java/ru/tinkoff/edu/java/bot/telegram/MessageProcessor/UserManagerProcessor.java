package ru.tinkoff.edu.java.bot.telegram.MessageProcessor;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import ru.tinkoff.edu.java.bot.telegram.commands.Command;

import java.util.List;

public interface UserManagerProcessor {

    List<? extends Command> commands();

    SendMessage process(Update update);
    void unregisterChat(Update update);
}