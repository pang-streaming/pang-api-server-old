ALTER TABLE products
    DROP FOREIGN KEY fk_products_on_user,
    DROP COLUMN fk_user_id,
    ADD COLUMN fk_store_id BINARY(16) NOT NULL,
    ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

CREATE TABLE stores
(
    id            BINARY(16)   NOT NULL,
    name          VARCHAR(255) NOT NULL,
    description   TEXT         NOT NULL,
    profile_image VARCHAR(512) NOT NULL,
    banner_image  VARCHAR(512) NOT NULL,
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fk_user_id    BINARY(16)   NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_stores_user_id FOREIGN KEY (fk_user_id)
        REFERENCES users(id) ON DELETE CASCADE
);

ALTER TABLE products
    ADD CONSTRAINT fk_products_store_id
        FOREIGN KEY (fk_store_id) REFERENCES stores(id) ON DELETE CASCADE;

CREATE TABLE stores_users
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    fk_store_id BINARY(16) NULL,
    fk_user_id  BINARY(16) NULL,
    created_at  TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_stores_users_store_id FOREIGN KEY (fk_store_id)
        REFERENCES stores(id) ON DELETE CASCADE,
    CONSTRAINT fk_stores_users_user_id FOREIGN KEY (fk_user_id)
        REFERENCES users(id) ON DELETE CASCADE
);
