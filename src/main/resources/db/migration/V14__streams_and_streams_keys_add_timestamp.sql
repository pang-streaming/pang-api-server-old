ALTER TABLE streams_keys ADD created_at datetime NULL;
ALTER TABLE streams_keys ADD modified_at datetime NULL;

ALTER TABLE streams_keys MODIFY created_at datetime NOT NULL;
ALTER TABLE streams_keys MODIFY modified_at datetime NOT NULL;

ALTER TABLE streams ADD created_at datetime NULL;
ALTER TABLE streams ADD modified_at datetime NULL;

ALTER TABLE streams MODIFY created_at datetime NOT NULL;
ALTER TABLE streams MODIFY modified_at datetime NOT NULL;

ALTER TABLE streams DROP COLUMN started_at;