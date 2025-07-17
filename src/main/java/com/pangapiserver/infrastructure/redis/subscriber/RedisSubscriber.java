package com.pangapiserver.infrastructure.redis.subscriber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pangapiserver.domain.common.exception.InternalServerErrorException;
import com.pangapiserver.infrastructure.redis.subscriber.data.LiveStatusEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {
    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String body = new String(message.getBody());
        try {
            LiveStatusEvent event = objectMapper.readValue(body, LiveStatusEvent.class);
            log.info("LiveStatusEvent: {} {}", event.streamerId(), event.isStreaming());
            //TODO 스트림 이벤트 구현하기
        } catch (JsonProcessingException e) {
            throw new InternalServerErrorException();
        }

    }
}
