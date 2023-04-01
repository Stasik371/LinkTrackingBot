package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.webclients.dto.link.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.webclients.interfaces.LinksClient;

@Component
public class TrackCommand implements Command {

    private final LinksClient linksClient;
    private String COMMAND = "/track";
    private String DESCRIPTION = COMMAND + " -> отследить ссылку.";

    private String GOOD_ANSWER = "Ссылка успешна добавлена";
    private String BAD_ANSWER = "Некорректная ссылка, бот поддерживает ссылки в формате URL";

    @Autowired
    public TrackCommand(LinksClient linksClient) {
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
        linksClient.addLink(update.message().chat().id(), new AddLinkRequest(update.message().text()));
        return new SendMessage(update.message().chat().id(), GOOD_ANSWER);
    }
}
