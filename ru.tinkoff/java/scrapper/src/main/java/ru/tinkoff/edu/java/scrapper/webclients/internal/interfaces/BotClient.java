package ru.tinkoff.edu.java.scrapper.webclients.internal.interfaces;


import ru.tinkoff.edu.java.scrapper.webclients.internal.dto.LinkUpdate;

public interface BotClient {


    LinkUpdate sendUpdates(LinkUpdate linkUpdate);
}
