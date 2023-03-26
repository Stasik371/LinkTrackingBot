package ru.tinkoff.edu.java.scrapper.util.exceptions;

public class LinkNotFoundException extends RuntimeException {
    public LinkNotFoundException(String message) {
        super(message);
    }
}
