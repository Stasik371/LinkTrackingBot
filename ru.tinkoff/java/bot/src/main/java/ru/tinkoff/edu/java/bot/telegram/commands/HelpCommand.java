package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand implements Command {

    private final String command = "/help";
    private final String description = "Все доступные команды.";

    private final String answer = "Список доступных команд: \n" +
            "/start - Запуск/перезапуск бота.\n" +
            "/help - Список доступных команд.\n" +
            "/track - Начать отслеживание ссылки.\n" +
            "/untrack - Перестать отслеживать ссылку.\n" +
            "/list - Список отслеживаемых ссылок.";

    @Override
    public String command() {
        return command;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), answer);
    }
}
