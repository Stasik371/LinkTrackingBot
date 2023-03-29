package ru.tinkoff.edu.java.scrapper.webclients.interfaces;

import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.webclients.dto.GitHubResponse;

@Component
public interface GitHubClient {
    GitHubResponse fetchRepositoryInfo(String user, String repository);
}
