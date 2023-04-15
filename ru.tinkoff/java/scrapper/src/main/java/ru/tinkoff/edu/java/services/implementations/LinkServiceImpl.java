package ru.tinkoff.edu.java.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.controllers.dto.response.LinkResponse;
import ru.tinkoff.edu.java.controllers.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.domain.LinkRepository;
import ru.tinkoff.edu.java.domain.TgChatRepository;
import ru.tinkoff.edu.java.domain.model.LinkModel;
import ru.tinkoff.edu.java.domain.jdbc.repository.JdbcLinkRepository;
import ru.tinkoff.edu.java.domain.jdbc.repository.JdbcTgChatRepository;
import ru.tinkoff.edu.java.services.interfaces.LinkService;
import ru.tinkoff.edu.java.util.exceptions.ChatNotFoundException;
import ru.tinkoff.edu.java.util.exceptions.LinkNotFoundException;
import ru.tinkoff.edu.java.util.exceptions.NoTrackedLinkException;
import ru.tinkoff.edu.java.util.exceptions.ReAddingALinkException;

import java.net.URI;

@Service
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    private final TgChatRepository tgChatRepository;

    @Autowired
    public LinkServiceImpl(JdbcLinkRepository linkRepository, JdbcTgChatRepository tgChatRepository) {
        this.linkRepository = linkRepository;
        this.tgChatRepository = tgChatRepository;
    }

    @Override
    public LinkResponse add(long tgChatId, URI url) {
        if (!tgChatRepository.existsById(tgChatId)) throw new ChatNotFoundException("Chat not found");
        if (linkRepository.existsByURIAndTgChatId(url, tgChatId))
            throw new ReAddingALinkException("LinkModel was added before");
        var linkModel = linkRepository.add(new LinkModel(tgChatId, url));
        return new LinkResponse(linkModel.tgChatId(), linkModel.uri().toString());
    }

    @Override
    public LinkResponse remove(long tgChatId, URI url) {
        if (!tgChatRepository.existsById(tgChatId)) throw new ChatNotFoundException("Chat not found");
        if (!linkRepository.existsByURIAndTgChatId(url, tgChatId)) throw new LinkNotFoundException("LinkModel not found");
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
