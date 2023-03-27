package ru.tinkoff.edu.java.scrapper.webclients.interfaces;

import ru.tinkoff.edu.java.scrapper.dto.StackOverFlowResponse;

public interface StackOverFlowClient {
    StackOverFlowResponse fetchQuestionInfo(String id);
}
