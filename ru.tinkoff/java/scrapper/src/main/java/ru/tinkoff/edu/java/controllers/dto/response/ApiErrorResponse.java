package ru.tinkoff.edu.java.controllers.dto.response;

public record ApiErrorResponse(String description, String code, String exceptionsName, String exceptionMessage,
                               String[] stacktrace) {
}