ALTER TABLE streams ADD created_at datetime NULL;
ALTER TABLE streams ADD modified_at datetime NULL;

UPDATE streams_keys
SET created_at = NOW(), modified_at = NOW()
WHERE created_at IS NULL OR modified_at IS NULL;

UPDATE streams
SET created_at = NOW(), modified_at = NOW()
WHERE created_at IS NULL OR modified_at IS NULL;

ALTER TABLE streams_keys
    MODIFY created_at DATETIME NOT NULL,
    MODIFY modified_at DATETIME NOT NULL;

ALTER TABLE streams
    MODIFY created_at DATETIME NOT NULL,
    MODIFY modified_at DATETIME NOT NULL;

ALTER TABLE streams DROP COLUMN started_at;
