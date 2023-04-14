package ru.tinkoff.edu.java.scrapper.webclients.internal.interfaces;


import org.springframework.http.HttpStatus;
import ru.tinkoff.edu.java.scrapper.webclients.internal.dto.LinkUpdate;

public interface BotClient {


    HttpStatus sendUpdates(LinkUpdate linkUpdate);
}
