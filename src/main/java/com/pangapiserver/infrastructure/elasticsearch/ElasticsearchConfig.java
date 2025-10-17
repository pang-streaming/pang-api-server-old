package com.pangapiserver.infrastructure.elasticsearch;

import com.pangapiserver.infrastructure.elasticsearch.exception.ElasticsearchConnectionException;
import com.pangapiserver.infrastructure.elasticsearch.properties.ElasticsearchProperties;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import javax.net.ssl.SSLContext;
import java.io.File;

@Configuration
@RequiredArgsConstructor
public class ElasticsearchConfig extends ElasticsearchConfiguration {
    private final ElasticsearchProperties properties;

    @Override
    public ClientConfiguration clientConfiguration() {
        try {
            SSLContext sslContext = SSLContextBuilder.create()
                    .loadTrustMaterial(new File(properties.getTruststorePath()), properties.getTruststorePassword().toCharArray())
                    .build();
            return ClientConfiguration.builder()
                    .connectedTo(properties.getUris())
                    .usingSsl(sslContext)
                    .withBasicAuth(properties.getUsername(), properties.getPassword())
                    .build();
        } catch (Exception e) {
            throw new ElasticsearchConnectionException();
        }
    }
}
