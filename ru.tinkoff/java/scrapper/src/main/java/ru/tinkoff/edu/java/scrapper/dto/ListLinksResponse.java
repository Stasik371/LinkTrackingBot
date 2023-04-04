package ru.tinkoff.edu.java.scrapper.dto;

import jakarta.validation.constraints.NotNull;

public record ListLinksResponse(@NotNull LinkResponse[] links, int size) {
}
