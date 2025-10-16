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
        String profileImage
) {
    public static StreamResponse of(StreamEntity stream) {
        return new StreamResponse(
                stream.getId(),
                stream.getTitle(),
                stream.getUrl(),
                stream.getUser().getUsername(),
                stream.getUser().getNickname(),
                stream.getUser().getProfileImage()
        );
    }

    public static StreamResponse of(StreamDocument document) {
        return new StreamResponse(
                document.getId(),
                document.getTitle(),
                document.getStreamUrl(),
                document.getUsername(),
                document.getNickname(),
                document.getProfileImage()
        );
    }
}
