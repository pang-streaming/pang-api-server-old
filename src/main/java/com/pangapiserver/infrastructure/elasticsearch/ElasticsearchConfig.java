package com.pangapiserver.infrastructure.elasticsearch;

import com.pangapiserver.infrastructure.elasticsearch.properties.ElasticsearchProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
        return ClientConfiguration.builder()
                .connectedTo(properties.getUris())
                .withBasicAuth(properties.getUsername(), properties.getPassword())
                .build();
    }

}
