package ru.tinkoff.edu.java.scrapper.webclients.outside.interfaces;

import org.springframework.stereotype.Component;

import ru.tinkoff.edu.java.linkparser.records.GithubRecord;
import ru.tinkoff.edu.java.scrapper.webclients.outside.dto.GitHubResponse;

@Component
public interface GitHubClient {
    GitHubResponse fetchRepositoryInfo(GithubRecord githubRecord);
}
