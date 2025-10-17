CREATE TABLE post_images (
    post_id BIGINT NOT NULL,
    image_url TEXT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts(id)
);