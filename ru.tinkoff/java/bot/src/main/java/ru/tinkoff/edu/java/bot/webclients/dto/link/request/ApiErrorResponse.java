package ru.tinkoff.edu.java.bot.webclients.dto.link.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiErrorResponse extends Exception {
    String description;
    String code;
    String exceptionsName;
    String exceptionMessage;
    String[] stacktrace;
}
