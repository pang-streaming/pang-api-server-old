CREATE TABLE temp_streams
(
    id                     BINARY(16)   NOT NULL,
    user_id                BINARY(16)   NOT NULL,
    uid                    VARCHAR(255) NOT NULL,
    web_rtc_url            TEXT         NULL,
    web_rtc_playback_url   TEXT         NULL,
    stream_name            VARCHAR(255) NULL,
    is_live                TINYINT(1)   DEFAULT 0,
    created_at             TIMESTAMP    NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at             TIMESTAMP    NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_temp_streams PRIMARY KEY (id),
    CONSTRAINT uk_temp_streams_uid UNIQUE (uid),
    CONSTRAINT fk_temp_streams_user FOREIGN KEY (user_id) REFERENCES users (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_temp_streams_user_id ON temp_streams (user_id);
