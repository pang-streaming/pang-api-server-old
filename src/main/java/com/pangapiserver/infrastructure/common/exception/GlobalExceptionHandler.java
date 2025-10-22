package com.pangapiserver.infrastructure.common.exception;

import com.pangapiserver.domain.common.exception.BasicException;
import com.pangapiserver.infrastructure.common.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class GlobalExceptionHandler {
    @ExceptionHandler(BasicException.class)
    public ResponseEntity<ErrorResponse> basicExceptionHandler(BasicException e) {
        return ErrorResponse.responseEntity(e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception e) {
        log.error("Exception Occurred", e);
        return ErrorResponse.responseEntity(
            GlobalStatusCode.INTERNAL_SERVER_ERROR.getHttpStatus(),
            e.getMessage()
        );
    }
}
