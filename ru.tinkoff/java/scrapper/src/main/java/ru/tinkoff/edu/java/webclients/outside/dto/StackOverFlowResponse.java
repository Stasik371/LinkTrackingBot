package ru.tinkoff.edu.java.webclients.outside.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotNull;


import java.time.OffsetDateTime;

public record StackOverFlowResponse(@NotNull @JsonSetter("last_activity_date") OffsetDateTime lastActivityDate,
                                    @NotNull @JsonSetter("answer_count") Integer answerCount) {
}
