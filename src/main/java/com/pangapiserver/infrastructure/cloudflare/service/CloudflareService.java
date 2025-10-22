package com.pangapiserver.infrastructure.cloudflare.service;

import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.cloudflare.data.StartStreamResponse;
import com.pangapiserver.infrastructure.cloudflare.properties.CloudflareProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudflareService {
    private final WebClient webClient;
    private final CloudflareProperties properties;

    public Mono<StartStreamResponse> createLiveInput(UserEntity user) {
        Map<String, Object> body = new HashMap<>();
        Map<String, Object> meta = new HashMap<>();
        meta.put("name", user.getNickname() + "'s Live Stream");
        body.put("meta", meta);

        Map<String, Object> recording = new HashMap<>();
        recording.put("mode", "automatic");
        body.put("recording", recording);

        String url = String.format("https://api.cloudflare.com/client/v4/accounts/%s/stream/live_inputs", properties.getAccountId());

        return webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + properties.getApiToken())
                .bodyValue(body)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    log.error("createLiveInput API error - status: {}, body: {}", response.statusCode(), errorBody);
                                    return Mono.error(new RuntimeException("Cloudflare API 호출 실패: " + errorBody));
                                })
                )
                .bodyToMono(StartStreamResponse.class)
                .doOnSuccess(response -> log.info("createLiveInput API response: {}", response))
                .doOnError(WebClientResponseException.class, e -> 
                        log.error("createLiveInput error - status: {}, body: {}", e.getStatusCode(), e.getResponseBodyAsString())
                );
    }

    public Mono<StartStreamResponse> getLiveInput(String liveInputUid) {
        String url = String.format("https://api.cloudflare.com/client/v4/accounts/%s/stream/live_inputs/%s/videos", properties.getAccountId(), liveInputUid);

        return webClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + properties.getApiToken())
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    log.error("getLiveInput API error - status: {}, body: {}", response.statusCode(), errorBody);
                                    return Mono.error(new RuntimeException("Cloudflare API 호출 실패: " + errorBody));
                                })
                )
                .bodyToMono(StartStreamResponse.class)
                .doOnSuccess(response -> log.info("getLiveInput API response: {}", response))
                .doOnError(WebClientResponseException.class, e ->
                        log.error("getLiveInput error - status: {}, body: {}", e.getStatusCode(), e.getResponseBodyAsString())
                );
    }
}
