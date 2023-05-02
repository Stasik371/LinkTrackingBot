package ru.tinkoff.edu.java.services.implementations.updatesenders;

import ru.tinkoff.edu.java.webclients.internal.dto.LinkUpdate;

public interface UpdateSender {
    void sendUpdates(LinkUpdate linkUpdate);
}
