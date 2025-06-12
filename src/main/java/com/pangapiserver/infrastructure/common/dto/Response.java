package com.pangapiserver.infrastructure.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private HttpStatus status;
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public static Response ok(String message) {
        return Response.builder()
                .status(HttpStatus.OK)
                .message(message)
                .build();
    }
}
