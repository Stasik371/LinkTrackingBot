package ru.tinkoff.edu.java.configuration;


import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ru.tinkoff.edu.java.configuration.domain.access.AccessType;
import ru.tinkoff.edu.java.util.scheduler.Scheduler;


@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(@NotNull Scheduler scheduler, @NotNull AccessType databaseAccessType,
                                String exchangeName, String queueName, boolean useQueue, int minutesToCheck,
                                String botBaseUrl) {
    @Bean
    public long schedulerIntervalInMs() {
        return scheduler.interval().toMillis();
    }
}
