package ru.tinkoff.edu.java.controllers.dto.response;

import jakarta.validation.constraints.NotNull;

public record ListLinksResponse(@NotNull LinkResponse[] links, int size) {
}
