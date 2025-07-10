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
public class DataResponse<T> {
    private HttpStatus status;
    private String message;
    private T data;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public static <T> DataResponse<T> created(String message, T data) {
        return DataResponse.<T>builder()
            .status(HttpStatus.CREATED)
            .message(message)
            .data(data)
            .build();
    }

    public static <T> DataResponse<T> ok(String message, T data) {
        return DataResponse.<T>builder()
            .status(HttpStatus.OK)
            .message(message)
            .data(data)
            .build();
    }

    public static <T> DataResponse<T> noContent(String message, T data) {
        return DataResponse.<T>builder()
            .status(HttpStatus.NO_CONTENT)
            .message(message)
            .data(data)
            .build();
    }
}