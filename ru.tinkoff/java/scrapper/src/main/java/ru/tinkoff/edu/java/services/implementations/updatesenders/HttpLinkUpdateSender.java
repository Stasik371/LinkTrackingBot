package ru.tinkoff.edu.java.services.implementations.updatesenders;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.edu.java.webclients.internal.dto.LinkUpdate;
import ru.tinkoff.edu.java.webclients.internal.sync.interfaces.BotClient;


public class HttpLinkUpdateSender implements UpdateSender {
    private final BotClient botClient;

    @Autowired
    public HttpLinkUpdateSender(BotClient botClient) {
        this.botClient = botClient;
    }

    @Override
    public void sendUpdates(LinkUpdate linkUpdate) {
        botClient.sendUpdates(linkUpdate);
    }
}