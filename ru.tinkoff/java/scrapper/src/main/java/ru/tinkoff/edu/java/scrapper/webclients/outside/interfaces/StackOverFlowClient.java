package ru.tinkoff.edu.java.scrapper.webclients.outside.interfaces;

import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.linkparser.records.StackOverFlowRecord;
import ru.tinkoff.edu.java.scrapper.webclients.outside.dto.StackOverFlowResponse;

@Component
public interface StackOverFlowClient {
    StackOverFlowResponse fetchQuestionInfo(StackOverFlowRecord stackOverFlowRecord);
}
