package ru.tinkoff.edu.java.scrapper.webclients.implementations;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.StackOverFlowResponse;
import ru.tinkoff.edu.java.scrapper.webclients.builders.StackOverFlowWebClientBuilder;
import ru.tinkoff.edu.java.scrapper.webclients.interfaces.StackOverFlowClient;

import java.util.List;


public class StackOverFlowClientBaseImpl implements StackOverFlowClient {
    private final WebClient client;

    public StackOverFlowClientBaseImpl(String url) {
        client = new StackOverFlowWebClientBuilder().build(url);
    }

    public StackOverFlowClientBaseImpl() {
        client = new StackOverFlowWebClientBuilder().build(null);
    }


    @Override
    public StackOverFlowResponse fetchQuestionInfo(String id) {
        record StackOverflowQuestionsRequest(@NotNull List<StackOverFlowResponse> items) {
        }
        return client.get()
                .uri("/questions/{id}?site=stackoverflow", id)
                .retrieve()
                .bodyToMono(StackOverflowQuestionsRequest.class)
                .block()
                .items()
                .get(0);
    }
}
