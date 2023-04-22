package ru.tinkoff.edu.java.webclients.internal.interfaces;


import org.springframework.http.HttpStatus;
import ru.tinkoff.edu.java.webclients.internal.dto.LinkUpdate;

public interface BotClient {

    HttpStatus sendUpdates(LinkUpdate linkUpdate);
}
