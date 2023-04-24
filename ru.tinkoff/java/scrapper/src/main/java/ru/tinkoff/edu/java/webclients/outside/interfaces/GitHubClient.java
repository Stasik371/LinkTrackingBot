package ru.tinkoff.edu.java.webclients.outside.interfaces;

import org.springframework.stereotype.Component;



import ru.tinkoff.edu.java.webclients.outside.dto.GitHubResponse;
import ru.tinkoff.edu.linkparser.records.GithubRecord;

@Component
public interface GitHubClient {
    GitHubResponse fetchRepositoryInfo(GithubRecord githubRecord);
}
