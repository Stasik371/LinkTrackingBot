package ru.tinkoff.edu.java.bot.webclients.interfaces;

import org.springframework.http.HttpStatus;

public interface TGChatClient {
    HttpStatus registerChat(long id);
    HttpStatus deleteChat(long id);
}
