package ru.tinkoff.edu.java.webclients;

import org.springframework.web.reactive.function.client.WebClient;

public interface WebClientBuilder {

    WebClient build(String baseUrl);
}
