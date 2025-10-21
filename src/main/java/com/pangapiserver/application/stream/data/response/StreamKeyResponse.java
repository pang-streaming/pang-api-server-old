package com.pangapiserver.application.stream.data.response;

import com.pangapiserver.domain.stream.entity.StreamKeyEntity;

public record StreamKeyResponse(
    String streamKey
) {
    public static StreamKeyResponse of(String streamKey) {
        if (streamKey == null) return null;
        return new StreamKeyResponse(streamKey);
    }
}
