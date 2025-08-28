CREATE TABLE products
(
    id          BINARY(16)   NOT NULL,
    image_url   TEXT NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    price       INT          NOT NULL,
    file_url    TEXT         NOT NULL,
    fk_user_id  BINARY(16)   NOT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id),
    CONSTRAINT fk_products_on_user FOREIGN KEY (fk_user_id) REFERENCES users (id)
);

CREATE TABLE product_likes
(
    id            BINARY(16)   NOT NULL,
    fk_product_id BINARY(16)   NOT NULL,
    fk_user_id    BINARY(16)   NOT NULL,
    created_at    DATETIME     NOT NULL,
    CONSTRAINT pk_product_likes PRIMARY KEY (id),
    CONSTRAINT fk_product_likes_on_product FOREIGN KEY (fk_product_id) REFERENCES products (id),
    CONSTRAINT fk_product_likes_on_user FOREIGN KEY (fk_user_id) REFERENCES users (id)
);

CREATE TABLE purchases
(
    id            BINARY(16)   NOT NULL,
    fk_product_id BINARY(16)   NOT NULL,
    fk_user_id    BINARY(16)   NOT NULL,
    created_at    DATETIME     NOT NULL,
    CONSTRAINT pk_purchases PRIMARY KEY (id),
    CONSTRAINT fk_purchases_on_product FOREIGN KEY (fk_product_id) REFERENCES products (id),
    CONSTRAINT fk_purchases_on_user FOREIGN KEY (fk_user_id) REFERENCES users (id)
);
