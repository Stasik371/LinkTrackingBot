package ru.tinkoff.edu.java.bot.controllers.dto;

public record ApiErrorResponse(String description, String code, String exceptionsName, String exceptionMessage,
                               String[] stacktrace) {

}
