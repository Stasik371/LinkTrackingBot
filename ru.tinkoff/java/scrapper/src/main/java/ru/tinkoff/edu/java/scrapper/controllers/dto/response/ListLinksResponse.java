package ru.tinkoff.edu.java.scrapper.controllers.dto.response;

import jakarta.validation.constraints.NotNull;
import ru.tinkoff.edu.java.scrapper.controllers.dto.response.LinkResponse;

public record ListLinksResponse(@NotNull LinkResponse[] links, int size) {
}
