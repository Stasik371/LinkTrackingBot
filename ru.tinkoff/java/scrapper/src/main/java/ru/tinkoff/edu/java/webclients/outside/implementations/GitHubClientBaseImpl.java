package ru.tinkoff.edu.java.webclients.outside.implementations;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.linkparser.records.GithubRecord;
import ru.tinkoff.edu.java.webclients.outside.dto.GitHubResponse;
import ru.tinkoff.edu.java.webclients.outside.interfaces.GitHubClient;

import java.time.Duration;


public class GitHubClientBaseImpl implements GitHubClient {
    @Value("${client.git-hub-base-url}")
    private String baseUrl;

    private final WebClient webClient;


    public GitHubClientBaseImpl(String url) {
        webClient = WebClient
                .builder()
                .baseUrl(url)
                .build();
    }

    public GitHubClientBaseImpl() {
        webClient = WebClient
                .builder()
                .baseUrl(baseUrl)
                .build();

    }

    @Override
    public GitHubResponse fetchRepositoryInfo(GithubRecord githubRecord) {
        System.out.println(githubRecord.user() + githubRecord.repository());
        return webClient
                .get()
                .uri("/repos/{user}/{repo}", githubRecord.user(), githubRecord.repository())
                .retrieve()
                .bodyToMono(GitHubResponse.class)
                .timeout(Duration.ofSeconds(10))
                .block();
    }
}
