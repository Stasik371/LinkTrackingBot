package ru.tinkoff.edu.java.bot.telegram.MessageProcessor;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import ru.tinkoff.edu.java.bot.telegram.commands.*;
import ru.tinkoff.edu.java.bot.webclients.dto.link.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.webclients.dto.link.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.ApiErrorResponse;
import ru.tinkoff.edu.java.bot.webclients.interfaces.ScrapperClient;

import java.util.Arrays;
import java.util.List;


public class UserManagerProcessorImpl implements UserManagerProcessor {

    private final String GOOD_TRACK_ANSWER = "Ссылка успешна добавлена";
    private final String GOOD_UNTRACK_ANSWER = "Ссылка успешно удалена";
    private final String TRACK_ANSWER = "Введите ссылку в формате URL, которую хотите отслеживать";
    private final String UNTRACK_ANSWER = "Введите ссылку в формате URL, которую хотите прекратить отслеживать";


    private final List<? extends Command> commands;

    private final ScrapperClient scrapperClient;



    @Override
    public List<? extends Command> commands() {
        return commands;
    }


    public UserManagerProcessorImpl(ScrapperClient scrapperClient, Command... commands) {
        this.scrapperClient = scrapperClient;
        this.commands = Arrays.stream(commands).toList();
    }

    @Override
    public SendMessage process(Update update) {
        for (var command : commands()) {
            if (command.supports(update)) return command.handle(update);
        }
        if (isReplyTrack(update)) {
            try {
                scrapperClient.addLink(update.message().chat().id(), new AddLinkRequest(update.message().text()));
                return new SendMessage(update.message().chat().id(), GOOD_TRACK_ANSWER);
            } catch (ApiErrorResponse errorResponse) {
                return new SendMessage(update.message().chat().id(), errorResponse.getExceptionMessage());
            }
        }
        if (isReplyUntrack(update)) {
            try {
                scrapperClient.deleteLink(update.message().chat().id(), new RemoveLinkRequest(update.message().text()));
                return new SendMessage(update.message().chat().id(), GOOD_UNTRACK_ANSWER);
            } catch (ApiErrorResponse errorResponse) {
                return new SendMessage(update.message().chat().id(), errorResponse.getExceptionMessage());
            }
        }
        return unsupportedCommand(update);
    }

    private boolean isReplyTrack(Update update) {
        if (update.message() == null) return false;
        Message reply = update.message().replyToMessage();
        return reply != null && reply.text().equals(TRACK_ANSWER);
    }

    private boolean isReplyUntrack(Update update) {
        if (update.message() == null) return false;
        Message reply = update.message().replyToMessage();
        return reply != null && reply.text().equals(UNTRACK_ANSWER);
    }

    private SendMessage unsupportedCommand(Update update) {
        return new SendMessage(update.message().chat().id(), "Команда не поддерживается, попробуйте /help");
    }

    @Override
    public void unregisterChat(Update update) {
        scrapperClient.deleteChat(update.myChatMember().chat().id());
    }
}
