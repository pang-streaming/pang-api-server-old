package com.pangapiserver.infrastructure.security.token.enumeration;

public enum TokenType {
    ACCESS,
    REFRESH;

    public static TokenType toTokenType(String value) {
        return TokenType.valueOf(value.toUpperCase());
    }
}
