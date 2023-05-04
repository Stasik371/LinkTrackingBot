package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;


@Component
public class UntrackCommand implements Command {

    private final String COMMAND = "/untrack";
    private final String DESCRIPTION = COMMAND + " -> перестать отслеживать ссылку.";
    private final String GOOD_ANSWER_BEFORE = "Введите ссылку в формате URL, которую хотите прекратить отслеживать";


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
        return new SendMessage(update.message().chat().id(), GOOD_ANSWER_BEFORE)
                .replyMarkup(new ForceReply().inputFieldPlaceholder("https://github.com/userName/repoName"));
    }
}