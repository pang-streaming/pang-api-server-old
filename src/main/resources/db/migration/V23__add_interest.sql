CREATE TABLE interest
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime              NOT NULL,
    modified_at datetime              NOT NULL,
    user_id     BINARY(16)            NULL,
    chip        VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_interest PRIMARY KEY (id)
);

ALTER TABLE interest
    ADD CONSTRAINT FK_INTEREST_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);