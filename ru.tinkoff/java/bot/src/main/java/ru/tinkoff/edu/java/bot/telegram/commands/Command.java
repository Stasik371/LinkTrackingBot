package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;


public interface Command {

    String command();

    String description();

    SendMessage handle(Update update);

    default boolean supports(Update update) {
        return switch (update.message().text()) {
            case "/help", "/list", "/start", "/track", "/untrack" -> true;
            default -> false;
        };
    }

    default BotCommand toApiCommand() {
        return new BotCommand(command(), description());
    }

}
