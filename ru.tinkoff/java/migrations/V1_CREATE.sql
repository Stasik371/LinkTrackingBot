create sequence if not exists chat_pk_sequence;
create table chat
(
    chat_id_pk       bigint primary key not null default nextval('chat_pk_sequence'),
    telegram_chat_id bigint unique      not null
);

alter sequence chat_pk_sequence owned by chat.chat_id_pk;

create sequence if not exists link_pk_sequence;

create table link
(
    link_id_pk      bigint primary key                                                            not null default nextval('link_pk_sequence'),
    chat_id         bigint references chat (telegram_chat_id) on update cascade on delete cascade not null,
    uri             text                                                                          not null,
    last_checked_at timestamp                                                                     not null
);