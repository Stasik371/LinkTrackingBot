package ru.tinkoff.edu.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.configuration.ClientConfiguration;
import ru.tinkoff.edu.java.webclients.outside.implementations.GitHubClientBaseImpl;
import ru.tinkoff.edu.java.webclients.outside.implementations.StackOverFlowClientBaseImpl;
import ru.tinkoff.edu.linkparser.records.GithubRecord;
import ru.tinkoff.edu.linkparser.records.StackOverFlowRecord;


@SpringBootApplication
@EnableConfigurationProperties({ApplicationConfig.class, ClientConfiguration.class})
public class ScrapperApplication {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(ScrapperApplication.class, args);
        var config = ctx.getBean(ClientConfiguration.class);
        System.out.println(config);
        var githubclient = ctx.getBean(GitHubClientBaseImpl.class, config.gitHubBaseUrl());
        System.out.println(githubclient.fetchRepositoryInfo(new GithubRecord("Stasik371", "TinkoffBot")));
        var stackoverflowclient = ctx.getBean(StackOverFlowClientBaseImpl.class, config.stackOverFlowBaseUrl());
        System.out.println(stackoverflowclient.fetchQuestionInfo(new StackOverFlowRecord("53167040")));
    }
}
