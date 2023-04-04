package ru.tinkoff.edu.java.scrapper.controllers.dto.response;

public record ApiErrorResponse(String description, String code, String exceptionsName, String exceptionMessage,
                               String[] stacktrace) {
}