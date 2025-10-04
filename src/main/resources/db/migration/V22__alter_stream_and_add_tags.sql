ALTER TABLE streams MODIFY fk_category_id BIGINT NULL;

CREATE TABLE stream_tags (
    stream_id BINARY(16) NOT NULL,
    tag VARCHAR(255),
    FOREIGN KEY (stream_id) REFERENCES streams(id)
);
