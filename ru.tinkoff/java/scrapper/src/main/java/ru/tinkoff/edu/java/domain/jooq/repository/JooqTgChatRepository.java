package ru.tinkoff.edu.java.domain.jooq.repository;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.edu.java.domain.TgChatRepository;
import ru.tinkoff.edu.java.domain.jooq.generated.tables.Chat;
import ru.tinkoff.edu.java.domain.model.TgChatModel;


import java.util.List;


public class JooqTgChatRepository implements TgChatRepository {
    private final DSLContext context;

    @Autowired
    public JooqTgChatRepository(DSLContext context) {
        this.context = context;
    }

    private final Chat chat = Chat.CHAT;


    @Override
    public List<TgChatModel> readAll() {
        return context
                .select()
                .from(chat)
                .stream()
                .map(record -> new TgChatModel(
                        record.getValue(chat.CHAT_ID_PK),
                        record.getValue(chat.TELEGRAM_CHAT_ID)))
                .toList();
    }

    @Override
    public void delete(long tgChatId) {
        context.deleteFrom(chat)
                .where(chat.TELEGRAM_CHAT_ID.eq(tgChatId))
                .execute();
    }

    @Override
    public void add(long tgChatId) {
        context.insertInto(chat, chat.TELEGRAM_CHAT_ID)
                .values(tgChatId)
                .execute();
    }

    @Override
    public Boolean existsById(long id) {
        return context
                .fetchExists(context
                        .selectFrom(chat)
                        .where(chat.TELEGRAM_CHAT_ID.eq(id)));
    }


}