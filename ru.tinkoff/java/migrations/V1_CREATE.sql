CREATE TABLE chat
(
    chat_id_pk       SERIAL PRIMARY KEY NOT NULL,
    telegram_chat_id INTEGER            NOT NULL
);

CREATE TABLE link
(
    link_id_pk      SERIAL PRIMARY KEY                                                       NOT NULL,
    chat_id         INTEGER REFERENCES chat (chat_id_pk) ON UPDATE CASCADE ON DELETE CASCADE NOT NULL,
    uri             VARCHAR(512)                                                             NOT NULL,
    last_checked_at TIME DEFAULT CURRENT_TIME
);