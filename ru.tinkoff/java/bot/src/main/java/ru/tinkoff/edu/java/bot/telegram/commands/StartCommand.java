package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.webclients.interfaces.TGChatClient;

@Component
public class StartCommand implements Command {
    private final TGChatClient tgChatClient;
    private final String COMMAND = "/start";
    private final String DESCRIPTION = "Запуск/перезапуск бота.";

    private final String ANSWER = "Привет! Чтобы получить список доступных комманд используйте /help";

    @Autowired
    public StartCommand(TGChatClient tgChatClient) {
        this.tgChatClient = tgChatClient;
    }

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
        tgChatClient.registerChat(update.message().chat().id());
        return new SendMessage(update.message().chat().id(), ANSWER);
    }
}
