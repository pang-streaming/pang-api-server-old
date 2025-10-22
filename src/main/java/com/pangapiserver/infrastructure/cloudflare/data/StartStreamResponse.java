package com.pangapiserver.infrastructure.cloudflare.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StartStreamResponse {
    private Boolean success;
    private Result result;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        private String uid;
        private Rtmps rtmps;
        private OffsetDateTime created;
        private OffsetDateTime modified;
        private Meta meta;
        private WebRTC webRTC;
        private WebRTCPlayback webRTCPlayback;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Rtmps {
        private String url;

        @JsonProperty("streamKey")
        private String streamKey;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Meta {
        private String name;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WebRTC {
        private String url;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WebRTCPlayback {
        private String url;
    }
}
