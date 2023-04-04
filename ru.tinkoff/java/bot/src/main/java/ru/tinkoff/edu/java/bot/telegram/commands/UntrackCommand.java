package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.webclients.dto.link.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.webclients.interfaces.LinksClient;

@Component
public class UntrackCommand implements Command {
    private final LinksClient linksClient;
    private final String COMMAND = "/untrack";
    private final String DESCRIPTION = "Перестать отслеживать ссылку.";

    private final String GOOD_ANSWER = "Ссылка успешна удалена";
    private final String GOOD_ANSWER_BEFORE = "Введите ссылку в формате URL, которую хотите прекратить отслеживать";
    private final String BAD_ANSWER = "Такой ссылки не существует, бот поддерживает ссылки в формате URL";

    public UntrackCommand(LinksClient linksClient) {
        this.linksClient = linksClient;
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
        var msg = update.message().text();
        if (msg.startsWith("/")) return new SendMessage(update.message().chat().id(), GOOD_ANSWER_BEFORE);
        linksClient.deleteLink(update.message().chat().id(), new RemoveLinkRequest(update.message().text()));
        return new SendMessage(update.message().chat().id(), GOOD_ANSWER);
    }
}
