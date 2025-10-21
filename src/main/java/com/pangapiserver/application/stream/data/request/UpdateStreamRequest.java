package com.pangapiserver.application.stream.data.request;

import com.pangapiserver.domain.stream.entity.StreamType;

import java.util.List;

public record UpdateStreamRequest (
        String title,
        Long categoryId,
        List<String> tags,
        String thumbnail,
        StreamType streamType
) {
}
