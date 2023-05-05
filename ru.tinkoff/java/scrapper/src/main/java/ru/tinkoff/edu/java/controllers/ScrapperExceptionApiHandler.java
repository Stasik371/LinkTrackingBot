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
    public ResponseEntity<ApiErrorResponse> httpMessageNotReadableEx(@NotNull HttpMessageNotReadableException ex) {
        var apiErrorResponse = new ApiErrorResponse(
            "No request Body",
            "400",
            "HttpMessageNotReadableException",
            ex.getMessage(),
            Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> webExchangeBindEx(@NotNull WebExchangeBindException ex) {
        var apiErrorResponse = new ApiErrorResponse(
            "LinkModel is not valid",
            "400",
            "webExchangeBindExceptionException(Rejected url)",
            ex.getMessage(),
            Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> chatNotFoundEx(@NotNull ChatNotFoundException ex) {
        var apiErrorResponse = new ApiErrorResponse(
            "Chat not found",
            "404",
            "ChatNotFoundException",
            ex.getMessage(),
            null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> methodNotAllowedEx(@NotNull MethodNotAllowedException ex) {
        var apiErrorResponse = new ApiErrorResponse(
            "Method not allowed",
            "405",
            "MethodNotAllowedException",
            ex.getMessage(),
            Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new)
        );
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(apiErrorResponse);
    }

    @ExceptionHandler(ReAddingALinkException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ApiErrorResponse> reAddingALinkEx(@NotNull ReAddingALinkException ex) {
        var apiErrorResponse = new ApiErrorResponse(
            "LinkModel has already been added",
            "405",
            "reAddingALinkException",
            ex.getMessage(),
            null
        );
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(apiErrorResponse);
    }

    @ExceptionHandler(LinkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> linkNotFoundEx(@NotNull LinkNotFoundException ex) {
        var apiErrorResponse = new ApiErrorResponse(
            "LinkModel not found",
            "404",
            "LinkNotFoundException",
            ex.getMessage(),
            null
        );
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(apiErrorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> illegalArgumentEx(@NotNull IllegalArgumentException ex) {
        var apiErrorResponse = new ApiErrorResponse(
            "Illegal arguments",
            "400",
            "IllegalArgumentException",
            ex.getMessage(),
            Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> reRegistrationEx(@NotNull DuplicateKeyException ex) {
        var apiErrorResponse = new ApiErrorResponse(
            "Chat was registered before",
            "400",
            ex.getClass().toString(),
            ex.getMessage(),
            null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler(NoTrackedLinkException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> noTrackedLinkEx(@NotNull NoTrackedLinkException ex) {
        var apiErrorResponse = new ApiErrorResponse(
            "No tracked links",
            "400",
            ex.getClass().toString(),
            ex.getMessage(),
            null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> unknownEx(@NotNull Exception ex) {
        var apiErrorResponse = new ApiErrorResponse(
            "Unknown exception, read stackTrace",
            "400",
            ex.getClass().toString(),
            ex.getMessage(),
            Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

}
