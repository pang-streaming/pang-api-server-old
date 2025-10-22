package com.pangapiserver.application.temp.data.request;

public record UpdateTempStreamRequest(
        String streamName,
        Boolean isLive
) {
}
