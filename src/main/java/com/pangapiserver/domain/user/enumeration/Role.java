package com.pangapiserver.domain.user.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER("ROLE_USER"),
    STREAMER("ROLE_STREAMER"),
    ADMIN("ROLE_ADMIN");

    private final String value;
}