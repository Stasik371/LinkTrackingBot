package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;


@Component
public class UntrackCommand implements Command {

    private final String command = "/untrack";
    private final String description = command + " -> перестать отслеживать ссылку.";
    private final String goodAnswerBefore = "Введите ссылку в формате URL, которую хотите прекратить отслеживать";


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
