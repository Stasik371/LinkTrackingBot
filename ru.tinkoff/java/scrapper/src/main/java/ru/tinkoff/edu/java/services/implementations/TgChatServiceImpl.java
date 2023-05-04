package ru.tinkoff.edu.java.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.domain.TgChatRepository;
import ru.tinkoff.edu.java.services.interfaces.TgChatService;

@Service
public class TgChatServiceImpl implements TgChatService {

    private final TgChatRepository tgChatRepository;

    @Autowired
    public TgChatServiceImpl(TgChatRepository tgChatRepository) {
        this.tgChatRepository = tgChatRepository;
    }

    @Override
    @Transactional
    public void register(long tgChatId) {
        tgChatRepository.add(tgChatId);
    }

    @Override
    @Transactional
    public void unregister(long tgChatId) {
        tgChatRepository.delete(tgChatId);
    }
}