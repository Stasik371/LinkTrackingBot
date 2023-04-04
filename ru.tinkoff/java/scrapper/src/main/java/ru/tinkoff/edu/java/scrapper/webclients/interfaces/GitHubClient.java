package ru.tinkoff.edu.java.scrapper.webclients.interfaces;

import ru.tinkoff.edu.java.scrapper.dto.GitHubResponse;

public interface GitHubClient {
    GitHubResponse fetchRepositoryInfo(String user, String repository);
}
