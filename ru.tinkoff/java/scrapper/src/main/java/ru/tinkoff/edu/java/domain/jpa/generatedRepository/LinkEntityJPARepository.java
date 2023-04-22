package ru.tinkoff.edu.java.domain.jpa.generatedRepository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.domain.jpa.Entity.LinkEntity;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface LinkEntityJPARepository extends JpaRepository<LinkEntity, Long> {

    @NotNull List<LinkEntity> findLinkEntitiesByChatTelegramChatId(Long tgChatId);

    @NotNull List<LinkEntity> findAll();

    void deleteLinkEntityByChatTelegramChatIdAndUri(Long telegramChatId, String uri);

    boolean existsByUriAndChatTelegramChatId(String uri, Long chat_telegramChatId);

    List<LinkEntity> findAllByUri(String uri);

    @Modifying
    @Query(value = "insert into link (chat_id, uri, last_checked_at, last_activity_at, issue_count, answer_count) " +
            "values(:chat_id, :uri, :last_checked_at, :last_activity_at, :issue_count, :answer_count) " +
            "on conflict do nothing",
            nativeQuery = true)
    void addLink(@Param("chat_id") Long tgChatId, @Param("uri") String uri,
                 @Param("last_checked_at") OffsetDateTime lastCheckedAt,
                 @Param("last_activity_at") OffsetDateTime lastActivityAt,
                 @Param("issue_count") Integer issueCount,
                 @Param("answer_count") Integer answerCount);
}
