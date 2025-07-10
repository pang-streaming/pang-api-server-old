package com.pangapiserver.infrastructure.redis.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SaveType {
    STREAM_KEY("stream_key::"),
    ;
    private final String value;
}