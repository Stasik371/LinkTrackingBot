package ru.tinkoff.edu.java.bot.webclients.builders;

import org.springframework.web.reactive.function.client.WebClient;

public interface WebClientBuilder {
    WebClient build(String baseUrl);
}
