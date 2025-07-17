package com.pangapiserver.infrastructure.redis.subscriber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pangapiserver.domain.common.exception.InternalServerErrorException;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.service.StreamService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.service.UserService;
import com.pangapiserver.infrastructure.redis.subscriber.data.LiveStatusEvent;
import com.pangapiserver.infrastructure.video.core.VideoUrlConvertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class )
public class RedisSubscriber implements MessageListener {
    private final ObjectMapper objectMapper;
    private final VideoUrlConvertor urlConvertor;
    private final UserService userService;
    private final StreamService streamService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String body = new String(message.getBody());
        try {
            LiveStatusEvent liveStatus = objectMapper.readValue(body, LiveStatusEvent.class);
            UserEntity user = userService.getByUsername(liveStatus.streamerId());
            if (liveStatus.isStreaming()) {
                streamService.save(liveStatus.toEntity(user, urlConvertor.setUrl(liveStatus.streamerId())));
            } else {
                StreamEntity stream = streamService.getByStreamingId(user);
                stream.updateEndAt();
                streamService.update(stream);
            }
        } catch (JsonProcessingException e) {
            throw new InternalServerErrorException();
        }
    }
}
