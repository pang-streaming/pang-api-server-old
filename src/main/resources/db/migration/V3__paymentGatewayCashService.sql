CREATE TABLE cards
(
    card_id        BINARY(16)   NOT NULL,
    number         VARCHAR(255) NULL,
    provider       VARCHAR(255) NULL,
    fk_user_id     BINARY(16)   NOT NULL,
    name           VARCHAR(255) NULL,
    phone          VARCHAR(255) NULL,
    encryption_key VARCHAR(255) NULL,
    CONSTRAINT pk_card PRIMARY KEY (card_id)
);

CREATE TABLE cashes
(
    id            BINARY(16)   NOT NULL,
    fk_user_id    BINARY(16)   NOT NULL,
    type          VARCHAR(255) NULL,
    amount        INT NOT NULL,
    created_at    datetime NULL,
    `description` VARCHAR(255) NULL,
    CONSTRAINT pk_cashes PRIMARY KEY (id)
);

ALTER TABLE cards
    ADD CONSTRAINT FK_CARD_ON_FK_USER FOREIGN KEY (fk_user_id) REFERENCES users (id);

ALTER TABLE cashes
    ADD CONSTRAINT FK_CASHES_ON_FK_USER FOREIGN KEY (fk_user_id) REFERENCES users (id);