package com.pangapiserver.infrastructure.s3.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.cloud.aws.s3")
public class S3Properties {
    private String bucket;
}
