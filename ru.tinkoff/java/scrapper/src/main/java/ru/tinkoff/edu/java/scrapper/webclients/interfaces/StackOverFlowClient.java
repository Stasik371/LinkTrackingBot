package ru.tinkoff.edu.java.scrapper.webclients.interfaces;

import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.webclients.dto.StackOverFlowResponse;

@Component
public interface StackOverFlowClient {
    StackOverFlowResponse fetchQuestionInfo(String id);
}
