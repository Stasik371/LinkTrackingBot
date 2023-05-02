package ru.tinkoff.edu.java.webclients.outside.implementations;

import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.linkparser.records.StackOverFlowRecord;
import ru.tinkoff.edu.java.webclients.outside.dto.StackOverFlowResponse;
import ru.tinkoff.edu.java.webclients.outside.interfaces.StackOverFlowClient;

import java.util.List;


public class StackOverFlowClientBaseImpl implements StackOverFlowClient {
    @Value("${client.stack-over-flow-base-url}")
    private String baseUrl;
    private final WebClient webClient;

    public StackOverFlowClientBaseImpl(String url) {
        webClient = WebClient
                .builder()
                .baseUrl(url)
                .build();
    }

    public StackOverFlowClientBaseImpl() {
        webClient = WebClient
                .builder()
                .baseUrl(baseUrl)
                .build();
    }


    @Override
    public StackOverFlowResponse fetchQuestionInfo(StackOverFlowRecord stackOverFlowRecord) {
        record StackOverflowQuestionsResponse(@NotNull List<StackOverFlowResponse> items) {
        }
        return webClient.get()
                .uri("/{id}?site=stackoverflow", stackOverFlowRecord.id())
                .retrieve()
                .bodyToMono(StackOverflowQuestionsResponse.class)
                .block()
                .items()
                .get(0);
    }
}
