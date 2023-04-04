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

    private String lastCommand;


    private final List<Command> commands;

    private Command searchCommandInList(String commandName) {
        return commands.stream()
                .filter(command -> command.command().equals(commandName))
                .findFirst().get();
    }

    @Override
    public List<? extends Command> commands() {
        return commands;
    }


    public UserManagerProcessorImpl(Command... commands) {
        this.commands = Arrays.stream(commands).toList();
    }

    @Autowired
    public UserManagerProcessorImpl(List<Command> commandList) {
        this.commands = commandList;
    }

    @Override
    public SendMessage process(Update update) {
        for (var command : commands) {
            if (command.command().equals(update.message().text())) {
                lastCommand = command.command();
                return command.handle(update);
            }
        }
        if (lastCommand.equals("/track")) {
            lastCommand = null;
            return searchCommandInList("/track").handle(update);
        }
        if (lastCommand.equals("/untrack")) {
            lastCommand = null;
            return searchCommandInList("/untrack").handle(update);
        }
        return unsupportedCommand(update);
    }

    private SendMessage unsupportedCommand(Update update) {
        return new SendMessage(update.message().chat().id(), "Команда не поддерживается, попробуйте /help");
    }
}
