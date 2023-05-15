package ru.tinkoff.edu.java.bot.controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.bot.controllers.dto.ApiErrorResponseDTO;

import java.util.Arrays;

@RestControllerAdvice
public class BotExceptionApiHandler {


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponseDTO> messageNotReadableEx(@NotNull HttpMessageNotReadableException ex) {
        var apiErrorResponse = new ApiErrorResponseDTO(
            "No request Body",
            "400",
            "HttpMessageNotReadableException",
            ex.getMessage(),
            Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponseDTO> methodArgumentNotValidEx(@NotNull MethodArgumentNotValidException ex) {
        var apiErrorResponse = new ApiErrorResponseDTO(
            "Arguments are not valid",
            "400",
            "MethodArgumentNotValidException",
            ex.getMessage(),
            Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponseDTO> illegalArgumentException(@NotNull IllegalArgumentException ex) {
        var apiErrorResponse = new ApiErrorResponseDTO(
            "Illegal arguments",
            "400",
            "IllegalArgumentException",
            ex.getMessage(),
            Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponseDTO> unknownException(@NotNull Exception exception) {
        var apiErrorResponse = new ApiErrorResponseDTO(
            "Unknown exception, read stackTrace",
            "400",
            exception.getClass().toString(),
            exception.getMessage(),
            Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }
}
