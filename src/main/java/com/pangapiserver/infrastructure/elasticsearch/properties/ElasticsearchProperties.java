package com.pangapiserver.infrastructure.elasticsearch.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.elasticsearch")
public class ElasticsearchProperties {
    private String username;
    private String password;
    private String uris;
}
