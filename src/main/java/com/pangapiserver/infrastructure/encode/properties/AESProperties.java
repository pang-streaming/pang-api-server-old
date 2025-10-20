package com.pangapiserver.infrastructure.encode.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.aes")
public class AESProperties {
    private String key;
    private String iv;
}
