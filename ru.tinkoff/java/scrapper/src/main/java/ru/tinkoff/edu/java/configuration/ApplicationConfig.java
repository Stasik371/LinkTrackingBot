package ru.tinkoff.edu.java.configuration;


import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
<<<<<<< HEAD
=======
import ru.tinkoff.edu.java.configuration.domain.access.AccessType;
>>>>>>> parent of 8b22849 (stage 2(and minor refactoring with app.configuration))
import ru.tinkoff.edu.java.util.scheduler.Scheduler;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
<<<<<<< HEAD
public record ApplicationConfig(@NotNull Scheduler scheduler) {
=======
public record ApplicationConfig(@NotNull Scheduler scheduler, @NotNull AccessType databaseAccessType) {
>>>>>>> parent of 8b22849 (stage 2(and minor refactoring with app.configuration))
    @Bean
    public long schedulerIntervalInMs(@NotNull ApplicationConfig config) {
        return config.scheduler().interval().toMillis();
    }
}


