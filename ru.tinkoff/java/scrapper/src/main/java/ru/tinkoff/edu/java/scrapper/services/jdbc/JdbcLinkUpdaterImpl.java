package ru.tinkoff.edu.java.scrapper.services.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.linkparser.parsers.ChainOfParsers;
import ru.tinkoff.edu.java.linkparser.records.GithubRecord;
import ru.tinkoff.edu.java.linkparser.records.StackOverFlowRecord;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.model.Link;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.model.TgChat;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.repository.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.repository.JdbcTgChatRepository;
import ru.tinkoff.edu.java.scrapper.services.LinkUpdater;
import ru.tinkoff.edu.java.scrapper.webclients.internal.dto.LinkUpdate;
import ru.tinkoff.edu.java.scrapper.webclients.internal.interfaces.BotClient;
import ru.tinkoff.edu.java.scrapper.webclients.outside.interfaces.GitHubClient;
import ru.tinkoff.edu.java.scrapper.webclients.outside.interfaces.StackOverFlowClient;

import java.time.OffsetDateTime;

@Service
public class JdbcLinkUpdaterImpl implements LinkUpdater {


    private final JdbcLinkRepository linkRepository;
    private final BotClient botClient;

    private final GitHubClient gitHubClient;
    private final StackOverFlowClient stackOverFlowClient;

    private final JdbcTgChatRepository chatRepository;


    @Autowired
    public JdbcLinkUpdaterImpl(JdbcLinkRepository linkRepository, BotClient botClient,
                               GitHubClient gitHubClient, StackOverFlowClient stackOverFlowClient,
                               JdbcTgChatRepository chatRepository) {
        this.linkRepository = linkRepository;
        this.botClient = botClient;
        this.gitHubClient = gitHubClient;
        this.stackOverFlowClient = stackOverFlowClient;
        this.chatRepository = chatRepository;
    }


    @Override
    public void update() {
        var links = linkRepository.readAllToUpdate();
        for (var link : links) {
            var record = ChainOfParsers.parse(link.uri());
            if (record instanceof GithubRecord) {
                var response = gitHubClient.fetchRepositoryInfo((GithubRecord) record);
                var newCheck = OffsetDateTime.now();
                var issueCount = link.issueCount();
                var lastPush = link.lastActivity();
                if (!response.issuesCount().equals(issueCount)) {
                    issueCount = response.issuesCount();
                    botClient.sendUpdates(new LinkUpdate(link.id(),
                            link.uri().toString(),
                            "Link " + link.uri() + " has new issue.",
                            chatRepository
                                    .readAllByURI(link.uri())
                                    .stream()
                                    .map(TgChat::tgChatId).mapToLong(val -> val)
                                    .toArray()));
                } else if (response.pushedAt().isAfter(lastPush)) {
                    lastPush = response.pushedAt();
                    botClient.sendUpdates(new LinkUpdate(link.id(),
                            link.uri().toString(),
                            "Link " + link.uri() + " has new updates.",
                            chatRepository
                                    .readAllByURI(link.uri())
                                    .stream()
                                    .map(TgChat::tgChatId).mapToLong(val -> val)
                                    .toArray()));
                }
                linkRepository.update(new Link(link.id(), link.tgChatId(), link.uri(),
                        newCheck, lastPush, issueCount, link.answerCount()));
            } else if (record instanceof StackOverFlowRecord) {
                var response = stackOverFlowClient.fetchQuestionInfo((StackOverFlowRecord) record);
                var newCheck = OffsetDateTime.now();
                var lastActivity = link.lastActivity();
                var answerCount = link.answerCount();
                if (!response.answerCount().equals(link.answerCount())) {
                    answerCount = response.answerCount();
                    botClient.sendUpdates(new LinkUpdate(link.id(),
                            link.uri().toString(),
                            "Link " + link.uri() + " has new questions.",
                            chatRepository
                                    .readAllByURI(link.uri())
                                    .stream()
                                    .map(TgChat::tgChatId).mapToLong(val -> val)
                                    .toArray()));
                } else if (!response.lastActivityDate().isAfter(link.lastActivity())) {
                    lastActivity = response.lastActivityDate();
                    botClient.sendUpdates(new LinkUpdate(link.id(),
                            link.uri().toString(),
                            "Link " + link.uri() + " has new updates.",
                            chatRepository
                                    .readAllByURI(link.uri())
                                    .stream()
                                    .map(TgChat::tgChatId).mapToLong(val -> val)
                                    .toArray()));
                }
                linkRepository.update(new Link(link.id(), link.tgChatId(), link.uri(),
                        newCheck, lastActivity, link.issueCount(), answerCount));
            }
        }

    }
}
