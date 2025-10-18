ALTER TABLE purchases
    ADD COLUMN fk_receiver_id BINARY(16);

ALTER TABLE purchases
    ADD CONSTRAINT fk_purchases_receiver
        FOREIGN KEY (fk_receiver_id) REFERENCES users(id);
