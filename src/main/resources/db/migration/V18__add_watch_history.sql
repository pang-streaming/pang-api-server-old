CREATE TABLE watch_histories
(
    id           BINARY(16) NOT NULL PRIMARY KEY,
    fk_user_id   BINARY(16) NOT NULL,
    fk_stream_id BINARY(16) NOT NULL,
    created_at   DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_watch_histories_user FOREIGN KEY (fk_user_id)
        REFERENCES users (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_watch_histories_stream FOREIGN KEY (fk_stream_id)
        REFERENCES streams (id)
        ON DELETE CASCADE
);
