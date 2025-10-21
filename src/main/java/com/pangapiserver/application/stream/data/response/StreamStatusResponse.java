package com.pangapiserver.application.stream.data.response;

import com.pangapiserver.domain.category.entity.CategoryEntity;
import com.pangapiserver.domain.stream.entity.StreamStatus;
import com.pangapiserver.domain.stream.entity.StreamType;

public record StreamStatusResponse(
        String title,
        CategoryEntity category,
        StreamStatus status,
        StreamType streamType
) {
    public static StreamStatusResponse of(String title, CategoryEntity category, StreamStatus status, StreamType type) {
        return new StreamStatusResponse(title, category, status, type);
    }
}
