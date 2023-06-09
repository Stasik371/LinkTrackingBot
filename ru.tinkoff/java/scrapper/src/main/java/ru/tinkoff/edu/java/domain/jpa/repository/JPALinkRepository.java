package ru.tinkoff.edu.java.domain.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.tinkoff.edu.java.domain.LinkRepository;
import ru.tinkoff.edu.java.domain.jpa.entity.LinkEntity;
import ru.tinkoff.edu.java.domain.jpa.generatedRepository.LinkEntityJPARepository;
import ru.tinkoff.edu.java.domain.model.LinkModel;
import ru.tinkoff.edu.java.domain.model.TgChatModel;
import ru.tinkoff.edu.java.util.exceptions.NoTrackedLinkException;

import java.net.URI;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class JPALinkRepository implements LinkRepository {

    private final LinkEntityJPARepository linkEntityJPARepository;

    @Value("${app.minutes-to-check}")
    private int minutesToCheck;

    @Autowired
    public JPALinkRepository(LinkEntityJPARepository linkEntityJPARepository) {
        this.linkEntityJPARepository = linkEntityJPARepository;
    }

    @Override
    public List<LinkModel> readAllToUpdate() {
        return mapFromEntityToModel(linkEntityJPARepository
                .findByLastCheckedAtBefore(OffsetDateTime.now().minus(minutesToCheck, ChronoUnit.MINUTES)));
    }

    @Override
    public List<LinkModel> readAllWithTgChatId(long tgChatId) {
        return mapFromEntityToModel(linkEntityJPARepository
                .findLinkEntitiesByChatTelegramChatId(tgChatId));
    }

    @Override
    public List<LinkModel> readAll() {
        return mapFromEntityToModel(linkEntityJPARepository.findAll());
    }

    @Override
    public URI delete(URI uri, long tgChatId) {
        linkEntityJPARepository.deleteLinkEntityByChatTelegramChatIdAndUri(tgChatId, uri.toString());
        return uri;
    }

    @Override
    public LinkModel add(LinkModel model) {
        linkEntityJPARepository.addLink(model.tgChatId(), model.uri().toString(),
                model.lastCheckedAt(), model.lastActivity(),
                model.issueCount(), model.answerCount());
        return model;
    }

    @Override
    public Boolean existsByURIAndTgChatId(URI uri, long tgChatId) {
        return linkEntityJPARepository.existsByUriAndChatTelegramChatId(uri.toString(), tgChatId);
    }

    @Override
    public void update(LinkModel link) {
        var linkEntity = linkEntityJPARepository.findById(link.id())
                .orElseThrow(() -> new NoTrackedLinkException("Link '" + link.uri() + "' was not found"));
        linkEntity.setLastCheckedAt(link.lastCheckedAt());
        linkEntity.setLastActivityAt(link.lastActivity());
        linkEntity.setIssueCount(link.issueCount());
        linkEntity.setAnswerCount(link.answerCount());
        linkEntityJPARepository.save(linkEntity);
    }

    @Override
    public List<TgChatModel> readAllByURI(URI uri) {
        return linkEntityJPARepository
                .findAllByUri(uri.toString())
                .stream()
                .map(l -> new TgChatModel(l.getChat().getTelegramChatId()))
                .toList();
    }


    private List<LinkModel> mapFromEntityToModel(List<LinkEntity> linksList) {
        return linksList
                .stream()
                .map(l -> new LinkModel(l.getLinkIdPk(), l.getChat().getTelegramChatId(),
                        URI.create(l.getUri()), l.getLastCheckedAt(), l.getLastActivityAt(),
                        l.getIssueCount(), l.getAnswerCount()))
                .toList();
    }
}