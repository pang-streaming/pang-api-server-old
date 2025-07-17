package com.pangapiserver.infrastructure.video.core;

import com.pangapiserver.infrastructure.video.properties.VideoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VideoUrlConvertor {
    private final VideoProperties properties;

    public String setUrl(String streamerId) {
        return properties.getKey().replace("[]", streamerId);
    }
}
