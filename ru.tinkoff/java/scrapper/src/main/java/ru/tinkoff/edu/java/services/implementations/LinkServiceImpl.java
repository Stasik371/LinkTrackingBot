package ru.tinkoff.edu.java.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.controllers.dto.response.LinkResponse;
import ru.tinkoff.edu.java.controllers.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.domain.LinkRepository;
import ru.tinkoff.edu.java.domain.TgChatRepository;
import ru.tinkoff.edu.java.domain.model.LinkModel;
import ru.tinkoff.edu.java.services.interfaces.LinkService;
import ru.tinkoff.edu.java.util.exceptions.*;
import ru.tinkoff.edu.java.webclients.outside.interfaces.GitHubClient;
import ru.tinkoff.edu.java.webclients.outside.interfaces.StackOverFlowClient;
import ru.tinkoff.edu.linkparser.parsers.ChainOfParsers;
import ru.tinkoff.edu.linkparser.records.GithubRecord;
import ru.tinkoff.edu.linkparser.records.StackOverFlowRecord;

import java.net.URI;

@Service
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    private final TgChatRepository tgChatRepository;

    private final GitHubClient gitHubClientBase;

    private final StackOverFlowClient stackOverFlowClient;

    @Autowired
    public LinkServiceImpl(LinkRepository linkRepository, TgChatRepository tgChatRepository,
                           GitHubClient gitHubClientBase, StackOverFlowClient stackOverFlowClient) {
        this.linkRepository = linkRepository;
        this.tgChatRepository = tgChatRepository;
        this.gitHubClientBase = gitHubClientBase;
        this.stackOverFlowClient = stackOverFlowClient;
    }


    @Override
    public LinkResponse add(long tgChatId, URI url) {
        if (!tgChatRepository.existsById(tgChatId)) throw new ChatNotFoundException("Chat not found");
        if (linkRepository.existsByURIAndTgChatId(url, tgChatId))
            throw new ReAddingALinkException("LinkModel was added before");
        try {
            var record = ChainOfParsers.parse(url);
            if (record instanceof GithubRecord) {
                var response = gitHubClientBase.fetchRepositoryInfo((GithubRecord) record);
                var linkModel = linkRepository.add(new LinkModel(tgChatId, url,
                        response.updatedAt(), response.issuesCount(), 0));
                return new LinkResponse(linkModel.tgChatId(), linkModel.uri().toString());
            } else if (record instanceof StackOverFlowRecord) {
                var response = stackOverFlowClient.fetchQuestionInfo((StackOverFlowRecord) record);
                var linkModel = linkRepository.add(new LinkModel(tgChatId, url,
                        response.lastActivityDate(), 0, response.answerCount()));
                return new LinkResponse(linkModel.tgChatId(), linkModel.uri().toString());
            }
            throw new NullPointerException("Link is not supported");
        } catch (NullPointerException e) {
            throw new NotSupportedLinkException(e.getMessage());
        }
    }

    @Override
    public LinkResponse remove(long tgChatId, URI url) {
        if (!tgChatRepository.existsById(tgChatId)) throw new ChatNotFoundException("Chat not found");
        if (!linkRepository.existsByURIAndTgChatId(url, tgChatId))
            throw new LinkNotFoundException("LinkModel not found");
        var uri = linkRepository.delete(url, tgChatId);
        return new LinkResponse(tgChatId, uri.toString());
    }

    @Override
    public ListLinksResponse listAll(long tgChatId) {
        if (!tgChatRepository.existsById(tgChatId)) throw new ChatNotFoundException("Chat not found");
        var links = linkRepository.readAllWithTgChatId(tgChatId);
        if (links.size() < 1) throw new NoTrackedLinkException("");
        return new ListLinksResponse(links
                .stream()
                .map(n -> new LinkResponse(n.tgChatId(), n.uri().toString()))
                .toArray(LinkResponse[]::new), links.size());
    }
}
