package ru.tinkoff.edu.java.webclients.outside.implementations;

import jakarta.validation.constraints.NotNull;

import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.linkparser.records.StackOverFlowRecord;
import ru.tinkoff.edu.java.webclients.outside.dto.StackOverFlowResponse;
import ru.tinkoff.edu.java.webclients.outside.builders.StackOverFlowWebClientBuilder;
import ru.tinkoff.edu.java.webclients.outside.interfaces.StackOverFlowClient;

import java.util.List;
import java.util.Objects;


public class StackOverFlowClientBaseImpl implements StackOverFlowClient {
    private final WebClient client;

    public StackOverFlowClientBaseImpl(String url) {
        client = new StackOverFlowWebClientBuilder().build(url);
    }

    public StackOverFlowClientBaseImpl() {
        client = new StackOverFlowWebClientBuilder().build(null);
    }


    @Override
    public StackOverFlowResponse fetchQuestionInfo(StackOverFlowRecord stackOverFlowRecord) {
        record StackOverflowQuestionsRequest(@NotNull List<StackOverFlowResponse> items) {
        }
        return Objects.requireNonNull(client.get()
                        .uri("/questions/{id}?site=stackoverflow", stackOverFlowRecord.id())
                        .retrieve()
                        .bodyToMono(StackOverflowQuestionsRequest.class)
                        .block())
                .items()
                .get(0);
    }
}
