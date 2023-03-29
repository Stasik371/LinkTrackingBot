package ru.tinkoff.edu.java.scrapper.util.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;

@Component
@EnableScheduling
public class LinkUpdaterScheduler {
    @Scheduled(fixedDelayString = "#{@schedulerIntervalInMs}")
    public void update() {
        System.out.println("LinkUpdaterScheduler log: " + OffsetDateTime.now());
    }
}
