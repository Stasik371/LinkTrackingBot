package ru.tinkoff.edu.java.scrapper.webclients.outside.dto;


import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record GitHubResponse(@NotNull @JsonSetter("full_name") String fullName,
                             @NotNull @JsonSetter("updated_at") OffsetDateTime updatedAt,
                             @NotNull @JsonSetter("pushed_at") OffsetDateTime pushedAt,
                             @NotNull @JsonSetter("open_issues_count") Integer issuesCount) {
}
