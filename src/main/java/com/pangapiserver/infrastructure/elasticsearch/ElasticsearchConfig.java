package com.pangapiserver.infrastructure.elasticsearch;

import com.pangapiserver.infrastructure.elasticsearch.exception.ElasticsearchConnectionException;
import com.pangapiserver.infrastructure.elasticsearch.properties.ElasticsearchProperties;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
@RequiredArgsConstructor
public class ElasticsearchConfig extends ElasticsearchConfiguration {
    private final ElasticsearchProperties properties;

    @Override
    public ClientConfiguration clientConfiguration() {
        try {
            return ClientConfiguration.builder()
                    .connectedTo(properties.getUris())
                    .usingSsl(SSLContextBuilder.create()
                            .loadTrustMaterial(new TrustAllStrategy())
                            .build())
                    .withBasicAuth(properties.getUsername(), properties.getPassword())
                    .build();
        } catch (Exception e) {
            throw new ElasticsearchConnectionException();
        }
    }
}
