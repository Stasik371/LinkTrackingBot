package ru.tinkoff.edu.java.scrapper.util.exceptions;

public class NoTrackedLinkException extends RuntimeException {
    public NoTrackedLinkException(String message) {
        super(message);
    }
}
