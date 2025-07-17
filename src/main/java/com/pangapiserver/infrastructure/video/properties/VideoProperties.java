package com.pangapiserver.infrastructure.video.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.video")
public class VideoProperties {
    private String key;
}
