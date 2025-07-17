package com.pangapiserver.infrastructure.redis.subscriber.data;

public record LiveStatusEvent(
    String streamerId,
    Boolean isStreaming
) {
}
