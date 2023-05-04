package ru.tinkoff.edu.java.domain.jpa.generatedRepository;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tinkoff.edu.java.domain.jpa.entity.TgChatEntity;

import java.util.List;


@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public interface TgChatEntityJPARepository extends JpaRepository<TgChatEntity, Long> {

    @Override
    @NotNull
    List<TgChatEntity> findAll();

    void deleteTgChatEntityByTelegramChatId(Long tgChatId);

    boolean existsByTelegramChatId(Long tgChatId);

    @Modifying
    @Query(value = "insert into chat (telegram_chat_id) values(:telegram_chat_id) on conflict do nothing",
            nativeQuery = true)
    void addTgChat(@Param("telegram_chat_id") Long tgChatId);
}