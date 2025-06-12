package com.pangapiserver.infrastructure.common.exception;

import com.pangapiserver.domain.common.exception.BasicException;
import com.pangapiserver.domain.common.exception.StatusCode;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler(BasicException.class)
    public ResponseEntity<ErrorResponse> basicExceptionHandler(BasicException e) {
        return ErrorResponse.responseEntity(e.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception e) {
        return ErrorResponse.responseEntity(GlobalStatusCode.INTERNAL_SERVER_ERROR);
    }
}
