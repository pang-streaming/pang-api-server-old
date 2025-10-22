package com.pangapiserver.infrastructure.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pangapiserver.domain.common.exception.BasicException;
import com.pangapiserver.domain.common.exception.StatusCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse extends Response {
    private final LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponse(HttpStatus status, String message) {
        super(status, message);
    }

    public static ResponseEntity<ErrorResponse> responseEntity(StatusCode statusCode) {
        return ResponseEntity
            .status(statusCode.getHttpStatus())
            .body(new ErrorResponse(
                statusCode.getHttpStatus(),
                statusCode.getMessage()
            ));
    }

    public static ResponseEntity<ErrorResponse> responseEntity(BasicException e) {
        return ResponseEntity
            .status(e.getStatusCode().getHttpStatus())
            .body(new ErrorResponse(
                e.getStatusCode().getHttpStatus(),
                e.getMessage()
            ));
    }

    public static ResponseEntity<ErrorResponse> responseEntity(HttpStatus status, String message) {
        return ResponseEntity
            .status(status.value())
            .body(new ErrorResponse(
                    status,
                    message
            ));
    }

    public static String setErrorBody(StatusCode errorCode) throws IOException {
        Response errorResponse = ErrorResponse.responseEntity(errorCode).getBody();

        ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(SerializationFeature.INDENT_OUTPUT);

        return mapper.writeValueAsString(errorResponse);
    }
}
