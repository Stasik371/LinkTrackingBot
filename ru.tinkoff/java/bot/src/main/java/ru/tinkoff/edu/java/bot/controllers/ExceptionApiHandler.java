package ru.tinkoff.edu.java.bot.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.bot.dto.ApiErrorResponse;

import java.util.Arrays;
import java.util.stream.Stream;

@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        var stacktrace = exception.getStackTrace();
        var apiErrorResponse = new ApiErrorResponse(
                "No request Body",
                "400",
                "HttpMessageNotReadableException",
                exception.getMessage(),
                Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new));
        return ResponseEntity.status(400).body(apiErrorResponse);
    }
}
