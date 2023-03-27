package ru.tinkoff.edu.java.scrapper.webclients.builders;

import org.springframework.web.reactive.function.client.WebClient;

public interface WebClientBuilder {

    WebClient build(String baseUrl);
}
