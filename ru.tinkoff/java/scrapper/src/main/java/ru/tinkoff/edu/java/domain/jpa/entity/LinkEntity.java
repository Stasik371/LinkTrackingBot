package ru.tinkoff.edu.java.domain.jpa.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "link")
public class LinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "link_pk_sequence")
    @SequenceGenerator(name = "link_pk_sequence", sequenceName = "link_pk_sequence", allocationSize = 1)
    @Column(name = "link_id_pk")
    private Long linkIdPk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", referencedColumnName = "telegram_chat_id",
            nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TgChatEntity chat;

    @Column(name = "uri", nullable = false)
    private String uri;

    @Column(name = "last_checked_at", nullable = false)
    private OffsetDateTime lastCheckedAt;

    @Column(name = "last_activity_at")
    private OffsetDateTime lastActivityAt;

    @Column(name = "issue_count")
    private Integer issueCount;

    @Column(name = "answer_count")
    private Integer answerCount;

}