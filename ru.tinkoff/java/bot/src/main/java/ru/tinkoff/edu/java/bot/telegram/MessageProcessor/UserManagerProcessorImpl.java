package ru.tinkoff.edu.java.bot.telegram.MessageProcessor;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.telegram.commands.*;
import java.util.Arrays;
import java.util.List;

@Component
public class UserManagerProcessorImpl implements UserManagerProcessor {

    private final List<? extends Command> commands;
    @Override
    public List<? extends Command> commands() {
        return commands;
    }

    @Autowired
    public UserManagerProcessorImpl(Command... commands) {
        this.commands = Arrays.stream(commands).toList();
    }

    @Override
    public SendMessage process(Update update) {
        for (var command : commands) {
            if (command.command().equals(update.message().text())) return command.handle(update);
        }
        return unsupportedCommand(update);
    }

    private SendMessage unsupportedCommand(Update update) {
        return new SendMessage(update.message().chat().id(), "Команда не поддерживается, попробуйте /help");
    }
}
