package ru.tinkoff.edu.java.scrapper.webclients.outside.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotNull;


import java.time.OffsetDateTime;

public record StackOverFlowResponse(@NotNull String link,
                                    @NotNull @JsonSetter("last_activity_date") OffsetDateTime lastActivityDate) {
}
