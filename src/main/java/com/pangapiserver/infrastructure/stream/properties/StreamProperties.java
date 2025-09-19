package com.pangapiserver.infrastructure.stream.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.stream")
public class StreamProperties {
    private String url;
}
