package com.pangapiserver.infrastructure.elasticsearch;

import com.pangapiserver.infrastructure.elasticsearch.exception.ElasticsearchConnectionException;
import com.pangapiserver.infrastructure.elasticsearch.properties.ElasticsearchProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import javax.net.ssl.SSLContext;
import java.io.File;

@Configuration
@Log4j2
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
            e.printStackTrace();
            log.error("Elasticsearch 연결에 실패했습니다. 메시지: {} 에러: {}", e.getMessage(), e);
            throw new ElasticsearchConnectionException();
        }
    }
}
