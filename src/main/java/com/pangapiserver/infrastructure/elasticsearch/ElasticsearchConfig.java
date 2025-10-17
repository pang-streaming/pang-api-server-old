package com.pangapiserver.infrastructure.elasticsearch;

import com.pangapiserver.infrastructure.elasticsearch.exception.ElasticsearchConnectionException;
import com.pangapiserver.infrastructure.elasticsearch.properties.ElasticsearchProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
@Log4j2
@RequiredArgsConstructor
public class ElasticsearchConfig extends ElasticsearchConfiguration {
    private final ElasticsearchProperties properties;

    @Override
    public ClientConfiguration clientConfiguration() {
        try {
            return ClientConfiguration.builder()
                    .connectedTo(properties.getUris())
                    .usingSsl(SSLContextBuilder.create()
                            .loadTrustMaterial(new TrustAllStrategy()).build())
                    .withBasicAuth(properties.getUsername(), properties.getPassword())
                    .build();
        } catch (Exception e) {
            log.error("Elasticsearch 연결에 실패했습니다. 메시지: {} 에러: {}", e.getMessage(), e);
            throw new ElasticsearchConnectionException();
        }
    }
}
