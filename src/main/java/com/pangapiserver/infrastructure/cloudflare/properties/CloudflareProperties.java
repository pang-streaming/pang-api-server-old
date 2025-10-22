package com.pangapiserver.infrastructure.cloudflare.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "cloudflare")
public class CloudflareProperties {
    private String accountId;
    private String apiToken;
}
