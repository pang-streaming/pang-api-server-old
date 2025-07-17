DROP TABLE streams;

CREATE TABLE streams
(
    id         BINARY(16)   NOT NULL,
    fk_user_id BINARY(16)   NOT NULL,
    title      VARCHAR(255) NOT NULL,
    url        VARCHAR(255) NOT NULL,
    started_at datetime     NOT NULL,
    end_at     datetime     NULL,
    CONSTRAINT pk_streams PRIMARY KEY (id)
);