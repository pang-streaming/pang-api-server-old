package com.pangapiserver.application.stream.data.request;

import java.util.List;

public record UpdateStreamRequest (
        String title,
        Long categoryId,
        List<String> tags,
        String thumbnail
) {
}
