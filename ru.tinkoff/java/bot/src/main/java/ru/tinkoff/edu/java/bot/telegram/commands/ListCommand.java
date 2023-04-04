package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.webclients.interfaces.LinksClient;


@Component
public class ListCommand implements Command {
    private final LinksClient linksClient;
    private final String COMMAND = "/list";
    private final String DESCRIPTION = "Все отслеживаемые ссылки.";

    private final String GOOD_ANSWER = "Список отслеживаемых ссылок:\n";
    private final String BAD_ANSWER = "Нет отслеживаемых ссылок";

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
        if (links.size() == 0) return new SendMessage(update.message().chat().id(), BAD_ANSWER);
        var answer = new StringBuilder();
        answer.append(GOOD_ANSWER);
        for (int i = 0; i < links.size(); i++) {
            answer.append(links.links()[i].url().toString() + "\n");
        }
        return new SendMessage(update.message().chat().id(), answer.toString());
    }
}
