package ru.tinkoff.edu.java.scrapper.util.exceptions;

public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException(String message) {
        super(message);
    }
}
