package com.pangapiserver.infrastructure.security.token;

import com.pangapiserver.infrastructure.security.properties.TokenProperties;
import com.pangapiserver.infrastructure.security.token.enumeration.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenParser {
    private final TokenProperties properties;

    public TokenType findType(String token) {
        return TokenType.toTokenType(createClaims(token).get("category", String.class));
    }

    public Date findExpiration(String token) {
        return createClaims(token).getExpiration();
    }

    public String findUsername(String token) {
        return createClaims(token).get("username", String.class);
    }

    private Claims createClaims(String token) {
        return Jwts.parser().verifyWith(properties.getSecretKey()).build().parseSignedClaims(token).getPayload();
    }
}
