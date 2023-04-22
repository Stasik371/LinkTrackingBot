package ru.tinkoff.edu.java.domain.jpa.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.domain.TgChatRepository;
import ru.tinkoff.edu.java.domain.jpa.generatedRepository.TgChatEntityJPARepository;
import ru.tinkoff.edu.java.domain.model.TgChatModel;

import java.util.List;

@Repository
@Primary
public class JPATgChatRepository implements TgChatRepository {

    @Autowired
    public JPATgChatRepository(TgChatEntityJPARepository tgChatEntityJPARepository) {
        this.tgChatEntityJPARepository = tgChatEntityJPARepository;
    }

    private final TgChatEntityJPARepository tgChatEntityJPARepository;

    @Override
    public List<TgChatModel> readAll() {
        return tgChatEntityJPARepository.findAll()
                .stream()
                .map(chat -> new TgChatModel(chat.getChatIdPk(),
                        chat.getTelegramChatId()))
                .toList();
    }

    @Override
    public void delete(long tgChatId) {
        tgChatEntityJPARepository.deleteTgChatEntityByTelegramChatId(tgChatId);
    }

    @Override
    public void add(long tgChatId) {
        tgChatEntityJPARepository.addTgChat(tgChatId);
    }

    @Override
    public Boolean existsById(long id) {
        return tgChatEntityJPARepository.existsByTelegramChatId(id);
    }
}
