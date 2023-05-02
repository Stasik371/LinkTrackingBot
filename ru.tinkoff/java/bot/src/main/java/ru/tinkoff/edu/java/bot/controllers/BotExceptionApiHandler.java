package ru.tinkoff.edu.java.bot.controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.bot.controllers.dto.ApiErrorResponse;

import java.util.Arrays;

@RestControllerAdvice
public class BotExceptionApiHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> httpMessageNotReadableException(@NotNull HttpMessageNotReadableException exception) {
        var apiErrorResponse = new ApiErrorResponse(
                "No request Body",
                "400",
                "HttpMessageNotReadableException",
                exception.getMessage(),
                Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new));
        return ResponseEntity.status(400).body(apiErrorResponse);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> methodArgumentNotValidException(@NotNull MethodArgumentNotValidException exception) {
        var apiErrorResponse = new ApiErrorResponse(
                "Arguments are not valid",
                "400",
                "MethodArgumentNotValidException",
                exception.getMessage(),
                Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new));
        return ResponseEntity.status(400).body(apiErrorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> illegalArgumentExceptionHandler(@NotNull IllegalArgumentException exception) {
        var apiErrorResponse = new ApiErrorResponse(
                "Illegal arguments",
                "400",
                "IllegalArgumentException",
                exception.getMessage(),
                Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new));
        return ResponseEntity.status(400).body(apiErrorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> unknownExceptionHandler(@NotNull Exception exception) {
        var apiErrorResponse = new ApiErrorResponse(
                "Unknown exception, read stackTrace",
                "400",
                exception.getClass().toString(),
                exception.getMessage(),
                Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new));
        return ResponseEntity.status(400).body(apiErrorResponse);
    }


}
