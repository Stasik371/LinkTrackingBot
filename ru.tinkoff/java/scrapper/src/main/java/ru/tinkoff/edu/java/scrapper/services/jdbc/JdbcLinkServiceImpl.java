package ru.tinkoff.edu.java.scrapper.services.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.controllers.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.controllers.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.model.Link;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.repository.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.repository.JdbcTgChatRepository;
import ru.tinkoff.edu.java.scrapper.services.LinkService;
import ru.tinkoff.edu.java.scrapper.util.exceptions.ChatNotFoundException;
import ru.tinkoff.edu.java.scrapper.util.exceptions.LinkNotFoundException;
import ru.tinkoff.edu.java.scrapper.util.exceptions.NoTrackedLinkException;
import ru.tinkoff.edu.java.scrapper.util.exceptions.ReAddingALinkException;

import java.net.URI;

@Service
public class JdbcLinkServiceImpl implements LinkService {

    private final JdbcLinkRepository linkRepository;

    private final JdbcTgChatRepository tgChatRepository;

    @Autowired
    public JdbcLinkServiceImpl(JdbcLinkRepository linkRepository, JdbcTgChatRepository tgChatRepository) {
        this.linkRepository = linkRepository;
        this.tgChatRepository = tgChatRepository;
    }

    @Override
    public LinkResponse add(long tgChatId, URI url) {
        if (!tgChatRepository.existsById(tgChatId)) throw new ChatNotFoundException("Chat not found");
        if (linkRepository.existsByURIAndTgChatId(url, tgChatId))
            throw new ReAddingALinkException("Link was added before");
        var linkModel = linkRepository.add(new Link(tgChatId, url));
        return new LinkResponse(linkModel.tgChatId(), linkModel.uri().toString());
    }

    @Override
    public LinkResponse remove(long tgChatId, URI url) {
        if (!tgChatRepository.existsById(tgChatId)) throw new ChatNotFoundException("Chat not found");
        if (!linkRepository.existsByURIAndTgChatId(url, tgChatId)) throw new LinkNotFoundException("Link not found");
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
