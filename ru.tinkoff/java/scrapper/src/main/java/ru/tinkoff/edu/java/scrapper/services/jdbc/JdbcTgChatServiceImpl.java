package ru.tinkoff.edu.java.scrapper.services.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.domain.jdbc.repository.JdbcTgChatRepository;
import ru.tinkoff.edu.java.scrapper.services.TgChatService;

@Service
public class JdbcTgChatServiceImpl implements TgChatService {

    private JdbcTgChatRepository tgChatRepository;

    @Autowired
    public JdbcTgChatServiceImpl(JdbcTgChatRepository tgChatRepository) {
        this.tgChatRepository = tgChatRepository;
    }

    @Override
    public void register(long tgChatId) {
        tgChatRepository.add(tgChatId);
    }

    @Override
    public void unregister(long tgChatId) {
        tgChatRepository.delete(tgChatId);
    }
}
