package com.pangapiserver.infrastructure.security.properties;

import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.token")
public class TokenProperties {
    private String secret;
    private Long accessToken;
    private Long refreshToken;

    public SecretKey getSecretKey() {
        return new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }
}
