package ru.tinkoff.edu.java.webclients.outside.interfaces;

import org.springframework.stereotype.Component;
import ru.tinkoff.edu.linkparser.records.StackOverFlowRecord;
import ru.tinkoff.edu.java.webclients.outside.dto.StackOverFlowResponse;

@Component
public interface StackOverFlowClient {
    StackOverFlowResponse fetchQuestionInfo(StackOverFlowRecord stackOverFlowRecord);
}
