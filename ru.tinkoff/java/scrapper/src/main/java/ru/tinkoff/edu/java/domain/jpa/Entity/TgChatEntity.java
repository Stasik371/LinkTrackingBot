package ru.tinkoff.edu.java.domain.jpa.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "chat")
@AllArgsConstructor
@NoArgsConstructor
public class TgChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_pk_sequence")
    @SequenceGenerator(name = "chat_pk_sequence", sequenceName = "chat_pk_sequence", allocationSize = 1)
    @Column(name = "chat_id_pk")
    private Long chatIdPk;

    @Column(name = "telegram_chat_id", unique = true, nullable = false)
    private Long telegramChatId;

    public TgChatEntity(Long telegramChatId) {
        this.telegramChatId = telegramChatId;
    }


/*@OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<LinkEntity> links;*/

}
