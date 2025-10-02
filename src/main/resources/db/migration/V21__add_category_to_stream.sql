ALTER TABLE streams ADD COLUMN fk_category_id BIGINT;

ALTER TABLE streams ADD CONSTRAINT fk_streams_category FOREIGN KEY (fk_category_id) REFERENCES category(id);
