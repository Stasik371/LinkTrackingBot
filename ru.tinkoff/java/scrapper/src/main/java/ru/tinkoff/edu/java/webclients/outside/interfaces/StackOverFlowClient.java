package ru.tinkoff.edu.java.webclients.outside.interfaces;


import ru.tinkoff.edu.linkparser.records.StackOverFlowRecord;
import ru.tinkoff.edu.java.webclients.outside.dto.StackOverFlowResponse;


public interface StackOverFlowClient {
    StackOverFlowResponse fetchQuestionInfo(StackOverFlowRecord stackOverFlowRecord);
}
