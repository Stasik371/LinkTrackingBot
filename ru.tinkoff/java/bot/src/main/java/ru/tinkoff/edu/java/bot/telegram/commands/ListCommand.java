package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.ApiErrorResponse;
import ru.tinkoff.edu.java.bot.webclients.interfaces.ScrapperClient;


@Component
public class ListCommand implements Command {
    private final ScrapperClient scrapperClient;
    private final String command = "/list";
    private final String description = command + " -> все отслеживаемые ссылки.";

    private final String goodAnswer = "Список отслеживаемых ссылок:\n";
    private final String badAnswer = "Нет отслеживаемых ссылок";

    @Autowired
    public ListCommand(ScrapperClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }


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
        try {
            var links = scrapperClient.getAllLinks(update.message().chat().id());
            if (links.size() == 0) return new SendMessage(update.message().chat().id(), badAnswer);
            var message = new StringBuilder();
            message.append(goodAnswer);
            for (int i = 0; i < links.size(); i++) {
                message.append(i + 1).append(") ").append(links.links()[i].url().toString()).append("\n\n");
            }
            return new SendMessage(update.message().chat().id(), message.toString());
        } catch (ApiErrorResponse errorResponse) {
            return new SendMessage(update.message().chat().id(), errorResponse.getDescription());
        }
    }
}
