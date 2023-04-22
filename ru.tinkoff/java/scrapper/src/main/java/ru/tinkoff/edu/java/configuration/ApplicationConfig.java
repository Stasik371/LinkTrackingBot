package ru.tinkoff.edu.java.configuration;


import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.java.configuration.domain.access.AccessType;
import ru.tinkoff.edu.java.util.scheduler.Scheduler;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(@NotNull Scheduler scheduler, @NotNull AccessType databaseAccessType) {
    @Bean
    public long schedulerIntervalInMs() {
        return scheduler.interval().toMillis();
    }
}


