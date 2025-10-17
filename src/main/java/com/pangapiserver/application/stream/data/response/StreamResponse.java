package com.pangapiserver.application.stream.data.response;

import com.pangapiserver.domain.stream.document.StreamDocument;
import com.pangapiserver.domain.stream.entity.StreamEntity;

import java.util.UUID;

public record StreamResponse(
        UUID streamId,
        String title,
        String url,
        String username,
        String nickname,
        // TODO: StreamStatus 추가
        String profileImage,
        int viewCount
) {
    public static StreamResponse of(StreamEntity stream, int viewCount) {
        return new StreamResponse(
                stream.getId(),
                stream.getTitle(),
                stream.getUrl(),
                stream.getUser().getUsername(),
                stream.getUser().getNickname(),
                stream.getUser().getProfileImage(),
                viewCount
        );
    }

    public static StreamResponse of(StreamDocument document, int viewCount) {
        return new StreamResponse(
                document.getStreamId(),
                document.getTitle(),
                document.getStreamUrl(),
                document.getUsername(),
                document.getNickname(),
                document.getProfileImage(),
                viewCount
        );
    }
}
