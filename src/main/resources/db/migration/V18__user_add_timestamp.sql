ALTER TABLE users
    ADD created_at datetime NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE users
    ADD modified_at datetime NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE users
    MODIFY created_at datetime NOT NULL;

ALTER TABLE users
    MODIFY modified_at datetime NOT NULL;