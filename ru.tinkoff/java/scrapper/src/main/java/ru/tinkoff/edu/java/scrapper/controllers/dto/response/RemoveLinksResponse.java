package ru.tinkoff.edu.java.scrapper.controllers.dto.response;

import org.hibernate.validator.constraints.URL;
import org.jetbrains.annotations.NotNull;

public record RemoveLinksResponse(@NotNull @URL String link) {
}
