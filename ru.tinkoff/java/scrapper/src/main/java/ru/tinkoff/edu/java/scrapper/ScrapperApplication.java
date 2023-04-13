package ru.tinkoff.edu.java.scrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.configuration.ClientConfiguration;
import ru.tinkoff.edu.java.scrapper.webclients.outside.implementations.GitHubClientBaseImpl;
import ru.tinkoff.edu.java.scrapper.webclients.outside.implementations.StackOverFlowClientBaseImpl;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationConfig.class, ClientConfiguration.class})
public class ScrapperApplication {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(ScrapperApplication.class, args);
        var config = ctx.getBean(ClientConfiguration.class);
        System.out.println(config);
        var githubclient = ctx.getBean(GitHubClientBaseImpl.class, config.gitHubBaseUrl());
        System.out.println(githubclient.fetchRepositoryInfo("Stasik371", "TinkoffBot"));
        var stackoverflowclient = ctx.getBean(StackOverFlowClientBaseImpl.class, config.stackOverFlowBaseUrl());
        System.out.println(stackoverflowclient.fetchQuestionInfo("53167040"));
    }
}
