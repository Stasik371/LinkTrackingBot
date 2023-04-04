package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand implements Command {

    private final String COMMAND = "/help";
    private final String DESCRIPTION = "Все доступные команды.";

    private final String ANSWER = "Список доступных команд: \n" +
            "/start - Запуск/перезапуск бота.\n" +
            "/help - Список доступных команд.\n" +
            "/track - Начать отслеживание ссылки.\n" +
            "/untrack - Перестать отслеживать ссылку.\n" +
            "/list - Список отслеживаемых ссылок.";

    @Override
    public String command() {
        return COMMAND;
    }

    @Override
    public String description() {
        return DESCRIPTION;
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), ANSWER);
    }
}
