package com.pangapiserver.infrastructure.common.dto;

import lombok.Getter;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pangapiserver.domain.common.exception.StatusCode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse extends Response{
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

    public static String setErrorBody(StatusCode errorCode) throws IOException {
        Response errorResponse = ErrorResponse.responseEntity(errorCode).getBody();

        ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(SerializationFeature.INDENT_OUTPUT);

        return mapper.writeValueAsString(errorResponse);
    }
}
