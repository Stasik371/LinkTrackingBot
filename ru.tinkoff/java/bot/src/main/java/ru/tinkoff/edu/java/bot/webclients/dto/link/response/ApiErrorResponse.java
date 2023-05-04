package ru.tinkoff.edu.java.bot.webclients.dto.link.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
@AllArgsConstructor
public class ApiErrorResponse extends RuntimeException {
    String description;
    String code;
    String exceptionName;
    String exceptionMessage;
    String[] stacktrace;

}