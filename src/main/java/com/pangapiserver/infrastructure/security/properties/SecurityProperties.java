package com.pangapiserver.infrastructure.security.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.security.user")
public class SecurityProperties {
    private String name;
    private String password;
}
