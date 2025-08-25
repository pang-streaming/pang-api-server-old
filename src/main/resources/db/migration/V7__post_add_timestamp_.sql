ALTER TABLE posts
    ADD created_at datetime NULL;

ALTER TABLE posts
    ADD modified_at datetime NULL;

ALTER TABLE posts
    MODIFY created_at datetime NOT NULL;

ALTER TABLE posts
    MODIFY modified_at datetime NOT NULL;