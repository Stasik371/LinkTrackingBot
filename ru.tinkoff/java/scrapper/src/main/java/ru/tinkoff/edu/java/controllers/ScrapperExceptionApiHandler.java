package ru.tinkoff.edu.java.controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.MethodNotAllowedException;
import ru.tinkoff.edu.java.controllers.dto.response.ApiErrorResponse;
import ru.tinkoff.edu.java.util.exceptions.ChatNotFoundException;
import ru.tinkoff.edu.java.util.exceptions.LinkNotFoundException;
import ru.tinkoff.edu.java.util.exceptions.NoTrackedLinkException;
import ru.tinkoff.edu.java.util.exceptions.ReAddingALinkException;

import java.util.Arrays;

@RestControllerAdvice
public class ScrapperExceptionApiHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> httpMessageNotReadableExceptionHandler(@NotNull HttpMessageNotReadableException exception) {
        var apiErrorResponse = new ApiErrorResponse(
                "No request Body",
                "400",
                "HttpMessageNotReadableException",
                exception.getMessage(),
                Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new));
        return ResponseEntity.status(400).body(apiErrorResponse);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> webExchangeBindExceptionHandler(@NotNull WebExchangeBindException exception) {
        var apiErrorResponse = new ApiErrorResponse(
                "LinkModel is not valid",
                "400",
                "webExchangeBindExceptionException(Rejected url)",
                exception.getMessage(),
                Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new));
        return ResponseEntity.status(400).body(apiErrorResponse);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> chatNotFoundExceptionHandler(@NotNull ChatNotFoundException exception) {
        var apiErrorResponse = new ApiErrorResponse(
                "Chat not found",
                "404",
                "ChatNotFoundException",
                exception.getMessage(),
                null);
        return ResponseEntity.status(404).body(apiErrorResponse);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> chatNotFoundExceptionHandler(@NotNull MethodNotAllowedException exception) {
        var apiErrorResponse = new ApiErrorResponse(
                "Method not allowed",
                "405",
                "MethodNotAllowedException",
                exception.getMessage(),
                Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new));
        return ResponseEntity.status(405).body(apiErrorResponse);
    }


    @ExceptionHandler(ReAddingALinkException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ApiErrorResponse> reAddingALinkExceptionHandler(@NotNull ReAddingALinkException exception) {
        var apiErrorResponse = new ApiErrorResponse(
                "LinkModel has already been added",
                "405",
                "reAddingALinkException",
                exception.getMessage(),
                null);
        return ResponseEntity.status(405).body(apiErrorResponse);
    }

    @ExceptionHandler(LinkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> linkNotFoundExceptionHandler(@NotNull LinkNotFoundException exception) {
        var apiErrorResponse = new ApiErrorResponse(
                "LinkModel not found",
                "404",
                "LinkNotFoundException",
                exception.getMessage(),
                null);
        return ResponseEntity.status(404).body(apiErrorResponse);
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

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> reRegistrationExceptionHandler(@NotNull DuplicateKeyException exception) {
        var apiErrorResponse = new ApiErrorResponse(
                "Chat was registered before",
                "400",
                exception.getClass().toString(),
                exception.getMessage(),
                null);
        return ResponseEntity.status(400).body(apiErrorResponse);
    }

    @ExceptionHandler(NoTrackedLinkException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> reRegistrationExceptionHandler(@NotNull NoTrackedLinkException exception) {
        var apiErrorResponse = new ApiErrorResponse(
                "No tracked links",
                "400",
                exception.getClass().toString(),
                exception.getMessage(),
                null);
        return ResponseEntity.status(400).body(apiErrorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> reRegestrationHandler(@NotNull Exception exception) {
        var apiErrorResponse = new ApiErrorResponse(
                "Unknown exception, read stackTrace",
                "400",
                exception.getClass().toString(),
                exception.getMessage(),
                Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new));
        return ResponseEntity.status(400).body(apiErrorResponse);
    }

}