CREATE TABLE accounts
(
    id                BINARY(16)   NOT NULL,
    fk_user_id        BINARY(16)   NOT NULL,
    account_number    VARCHAR(255) NULL,
    account_bank_code VARCHAR(255) NULL,
    account_name      VARCHAR(255) NULL,
    account_type      VARCHAR(255) NULL,
    CONSTRAINT pk_accounts PRIMARY KEY (id)
);

CREATE TABLE alarm
(
    alarm_id   BINARY(16)   NOT NULL,
    fk_user_id BINARY(16)   NOT NULL,
    content    VARCHAR(255) NULL,
    title      VARCHAR(255) NULL,
    uri        VARCHAR(255) NULL,
    CONSTRAINT pk_alarm PRIMARY KEY (alarm_id)
);

CREATE TABLE badge
(
    id             INT AUTO_INCREMENT NOT NULL,
    `description`  VARCHAR(255) NOT NULL,
    image          VARCHAR(255) NOT NULL,
    fk_streamer_id BINARY(16)         NOT NULL,
    fk_user_id     BINARY(16)         NOT NULL,
    CONSTRAINT pk_badge PRIMARY KEY (id)
);

CREATE TABLE comments
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    fk_user_id    BINARY(16)            NOT NULL,
    content       TEXT   NOT NULL,
    pk_mantion_id BIGINT NOT NULL,
    pk_post_id    INT    NOT NULL,
    CONSTRAINT pk_comments PRIMARY KEY (id)
);

CREATE TABLE communites
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    fk_user_id    BINARY(16)            NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    CONSTRAINT pk_communites PRIMARY KEY (id)
);

CREATE TABLE donation
(
    id             INT AUTO_INCREMENT NOT NULL,
    content        VARCHAR(255) NULL,
    fk_user_id     BINARY(16)         NOT NULL,
    fk_streamer_id BINARY(16)         NOT NULL,
    price          INT NOT NULL,
    CONSTRAINT pk_donation PRIMARY KEY (id)
);

CREATE TABLE fcmdevice
(
    token_id   BINARY(16) NOT NULL,
    fk_user_id BINARY(16) NOT NULL,
    is_alarm   BIT(1) NOT NULL,
    CONSTRAINT pk_fcmdevice PRIMARY KEY (token_id)
);

CREATE TABLE follows
(
    id             INT AUTO_INCREMENT NOT NULL,
    fk_follower_id BINARY(16)         NOT NULL,
    fk_user_id     BINARY(16)         NOT NULL,
    is_alarm       BIT(1) NOT NULL,
    CONSTRAINT pk_follows PRIMARY KEY (id)
);

CREATE TABLE main_category
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_main_category PRIMARY KEY (id)
);

CREATE TABLE notification
(
    id          INT AUTO_INCREMENT NOT NULL,
    title       VARCHAR(255) NULL,
    content     VARCHAR(255) NULL,
    created_at  datetime NULL,
    modified_at datetime NULL,
    CONSTRAINT pk_notification PRIMARY KEY (id)
);

CREATE TABLE posts
(
    id                INT AUTO_INCREMENT NOT NULL,
    title             VARCHAR(255) NOT NULL,
    fk_user_id        BINARY(16)         NOT NULL,
    content           TEXT         NOT NULL,
    fk_communities_id BIGINT       NOT NULL,
    CONSTRAINT pk_posts PRIMARY KEY (id)
);

CREATE TABLE streams
(
    id         INT AUTO_INCREMENT NOT NULL,
    fk_user_id BINARY(16)         NOT NULL,
    title      VARCHAR(255) NOT NULL,
    url        VARCHAR(255) NOT NULL,
    started_at datetime     NOT NULL,
    end_at     datetime NULL,
    CONSTRAINT pk_streams PRIMARY KEY (id)
);

CREATE TABLE streams_keys
(
    id         INT AUTO_INCREMENT NOT NULL,
    fk_user_id BINARY(16)         NOT NULL,
    stream_key BINARY(16)         NOT NULL,
    CONSTRAINT pk_streams_keys PRIMARY KEY (id)
);

CREATE TABLE streams_tags
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_streams_tags PRIMARY KEY (id)
);

CREATE TABLE sub_category
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    name             VARCHAR(255) NULL,
    fk_main_category BIGINT NOT NULL,
    post_image       TEXT NULL,
    CONSTRAINT pk_sub_category PRIMARY KEY (id)
);

CREATE TABLE subscribes
(
    id             INT AUTO_INCREMENT NOT NULL,
    content        VARCHAR(255) NULL,
    fk_user_id     BINARY(16)         NOT NULL,
    fk_streamer_id BINARY(16)         NOT NULL,
    price          INT NOT NULL,
    CONSTRAINT pk_subscribes PRIMARY KEY (id)
);

CREATE TABLE users
(
    id            BINARY(16)   NOT NULL,
    username      VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    nickname      VARCHAR(255) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    age           date NULL,
    gender        VARCHAR(255) NULL,
    profile_image TEXT NULL,
    banner_image  TEXT NULL,
    is_adult      BIT(1)       NOT NULL,
    `role`        VARCHAR(255) NOT NULL,
    is_alarm      BIT(1)       NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_nickname UNIQUE (nickname);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE accounts
    ADD CONSTRAINT FK_ACCOUNTS_ON_FK_USER FOREIGN KEY (fk_user_id) REFERENCES users (id);

ALTER TABLE alarm
    ADD CONSTRAINT FK_ALARM_ON_FK_USER FOREIGN KEY (fk_user_id) REFERENCES users (id);

ALTER TABLE badge
    ADD CONSTRAINT FK_BADGE_ON_FK_STREAMER FOREIGN KEY (fk_streamer_id) REFERENCES users (id);

ALTER TABLE badge
    ADD CONSTRAINT FK_BADGE_ON_FK_USER FOREIGN KEY (fk_user_id) REFERENCES users (id);

ALTER TABLE comments
    ADD CONSTRAINT FK_COMMENTS_ON_FK_USER FOREIGN KEY (fk_user_id) REFERENCES users (id);

ALTER TABLE communites
    ADD CONSTRAINT FK_COMMUNITES_ON_FK_USER FOREIGN KEY (fk_user_id) REFERENCES users (id);

ALTER TABLE donation
    ADD CONSTRAINT FK_DONATION_ON_FK_STREAMER FOREIGN KEY (fk_streamer_id) REFERENCES users (id);

ALTER TABLE donation
    ADD CONSTRAINT FK_DONATION_ON_FK_USER FOREIGN KEY (fk_user_id) REFERENCES users (id);

ALTER TABLE fcmdevice
    ADD CONSTRAINT FK_FCMDEVICE_ON_FK_USER FOREIGN KEY (fk_user_id) REFERENCES users (id);

ALTER TABLE follows
    ADD CONSTRAINT FK_FOLLOWS_ON_FK_FOLLOWER FOREIGN KEY (fk_follower_id) REFERENCES users (id);

ALTER TABLE follows
    ADD CONSTRAINT FK_FOLLOWS_ON_FK_USER FOREIGN KEY (fk_user_id) REFERENCES users (id);

ALTER TABLE posts
    ADD CONSTRAINT FK_POSTS_ON_FK_COMMUNITIES FOREIGN KEY (fk_communities_id) REFERENCES communites (id);

ALTER TABLE posts
    ADD CONSTRAINT FK_POSTS_ON_FK_USER FOREIGN KEY (fk_user_id) REFERENCES users (id);

ALTER TABLE streams_keys
    ADD CONSTRAINT FK_STREAMS_KEYS_ON_FK_USER FOREIGN KEY (fk_user_id) REFERENCES users (id);

ALTER TABLE streams
    ADD CONSTRAINT FK_STREAMS_ON_FK_USER FOREIGN KEY (fk_user_id) REFERENCES users (id);

ALTER TABLE subscribes
    ADD CONSTRAINT FK_SUBSCRIBES_ON_FK_STREAMER FOREIGN KEY (fk_streamer_id) REFERENCES users (id);

ALTER TABLE subscribes
    ADD CONSTRAINT FK_SUBSCRIBES_ON_FK_USER FOREIGN KEY (fk_user_id) REFERENCES users (id);

ALTER TABLE sub_category
    ADD CONSTRAINT FK_SUB_CATEGORY_ON_FK_MAIN_CATEGORY FOREIGN KEY (fk_main_category) REFERENCES main_category (id);