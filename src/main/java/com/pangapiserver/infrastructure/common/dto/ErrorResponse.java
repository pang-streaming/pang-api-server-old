package com.pangapiserver.infrastructure.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pangapiserver.domain.common.exception.StatusCode;
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
}
