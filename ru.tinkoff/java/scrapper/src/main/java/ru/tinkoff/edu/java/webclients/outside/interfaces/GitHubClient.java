package ru.tinkoff.edu.java.webclients.outside.interfaces;


import ru.tinkoff.edu.java.webclients.outside.dto.GitHubResponse;
import ru.tinkoff.edu.linkparser.records.GithubRecord;


public interface GitHubClient {

    GitHubResponse fetchRepositoryInfo(GithubRecord githubRecord);
}
