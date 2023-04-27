package ru.tinkoff.edu.java.bot.update.senders;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.edu.java.bot.controllers.dto.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.telegram.bot.Bot;

public class HttpUpdateListener implements UpdateSender{

    private final Bot bot;

    @Autowired
    public HttpUpdateListener(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void receiver(LinkUpdateRequest update) {
        bot.sendUpdates(update);
    }
}
