package ru.tinkoff.edu.java.bot.update.senders;

import ru.tinkoff.edu.java.bot.controllers.dto.LinkUpdateRequest;

public interface UpdateSender {
    void receiver(LinkUpdateRequest update);
}
