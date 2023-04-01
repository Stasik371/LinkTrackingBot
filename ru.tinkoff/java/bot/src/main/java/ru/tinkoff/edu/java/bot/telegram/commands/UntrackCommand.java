package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.webclients.dto.link.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.webclients.interfaces.LinksClient;

@Component
public class UntrackCommand implements Command {
    private final LinksClient linksClient;
    private String COMMAND = "/untrack";
    private String DESCRIPTION = COMMAND + " -> перестать отслеживать ссылку.";

    private String GOOD_ANSWER = "Ссылка успешна удалена";
    private String BAD_ANSWER = "Такой ссылки не существует, бот поддерживает ссылки в формате URL";

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
        linksClient.deleteLink(update.message().chat().id(), new RemoveLinkRequest(update.message().text()));
        return new SendMessage(update.message().chat().id(), GOOD_ANSWER);
    }
}
