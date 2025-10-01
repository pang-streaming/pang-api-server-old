ALTER TABLE sub_category
    DROP FOREIGN KEY FK_SUB_CATEGORY_ON_FK_MAIN_CATEGORY;

CREATE TABLE category
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime              NOT NULL,
    modified_at datetime              NOT NULL,
    name        VARCHAR(255)          NOT NULL,
    chip        VARCHAR(255)          NOT NULL,
    post_image  TEXT                  NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

DROP TABLE main_category;

DROP TABLE sub_category;