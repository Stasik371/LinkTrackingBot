package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.webclients.interfaces.LinksClient;


@Component
public class ListCommand implements Command {
    private final LinksClient linksClient;
    private String COMMAND = "/list";
    private String DESCRIPTION = COMMAND + " -> все отслеживаемые ссылки.";

    private String ANSWER = "Список отслеживаемых ссылок:\n";

    @Autowired
    public ListCommand(LinksClient linksClient) {
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
        var links = linksClient.getAllLinks(update.message().chat().id());
        var answer = new StringBuilder();
        answer.append(ANSWER);
        for (int i = 0; i < links.size(); i++) {
            answer.append(links.links()[i].url().toString() + "\n");
        }
        return new SendMessage(update.message().chat().id(), answer.toString());
    }
}
