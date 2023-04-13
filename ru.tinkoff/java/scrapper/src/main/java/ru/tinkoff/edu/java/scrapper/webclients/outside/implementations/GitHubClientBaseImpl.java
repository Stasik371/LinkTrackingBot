package ru.tinkoff.edu.java.scrapper.webclients.outside.implementations;


import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.webclients.outside.builders.GitHubWebClientBuilder;
import ru.tinkoff.edu.java.scrapper.webclients.outside.dto.GitHubResponse;
import ru.tinkoff.edu.java.scrapper.webclients.outside.interfaces.GitHubClient;


public class GitHubClientBaseImpl implements GitHubClient {

    private final WebClient client;

    public GitHubClientBaseImpl(String url) {
        client = new GitHubWebClientBuilder().build(url);
    }

    public GitHubClientBaseImpl() {
        client = new GitHubWebClientBuilder().build(null);
    }

    @Override
    public GitHubResponse fetchRepositoryInfo(String user, String repository) {
        return client.get()
                .uri("/repos/{user}/{repo}", user, repository)
                .retrieve()
                .bodyToMono(GitHubResponse.class)
                .block();
    }
}
