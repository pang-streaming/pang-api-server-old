package com.pangapiserver.infrastructure.common.exception;

import com.pangapiserver.domain.common.exception.BasicException;
import com.pangapiserver.domain.common.exception.StatusCode;
import com.pangapiserver.infrastructure.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler(BasicException.class)
    private ResponseEntity<BaseResponse> handleBasicException(BasicException e) {
        StatusCode statusCode = e.getStatusCode();
        BaseResponse response = BaseResponse.error(
                statusCode.getHttpStatus(),
                statusCode.getMessage()
        );
        return ResponseEntity.status(statusCode.getHttpStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<BaseResponse> handleAll(Exception e) {
        BaseResponse response = BaseResponse.error(
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
