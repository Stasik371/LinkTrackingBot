package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.webclients.interfaces.ScrapperClient;

@Component
public class TrackCommand implements Command {

    private final ScrapperClient scrapperClient;
    private String command = "/track";
    private String description = command + " -> отследить ссылку.";

    private String goodAnswerBefore = "Введите ссылку в формате URL, которую хотите отслеживать";

    @Autowired
    public TrackCommand(ScrapperClient scrapperClient) {
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
        return new SendMessage(update.message().chat().id(), goodAnswerBefore)
                .replyMarkup(new ForceReply().inputFieldPlaceholder("https://github.com/userName/repoName"));
    }
}
